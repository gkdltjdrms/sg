import numpy as np
from sklearn.datasets import load_wine
from tensorflow.keras.models import Sequential,Model
from tensorflow.keras.layers import Dense,Input,Conv1D, Dropout,LSTM, Conv2D, Flatten
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score
from sklearn.preprocessing import MinMaxScaler, StandardScaler

#1. 데이터
datasets = load_wine()
x = datasets.data
y = datasets.target

print(x.shape, y.shape) # (178, 13) (178,)
print(y)
print(np.unique(y)) # [0 1 2]
print(np.unique(y, return_counts=True)) # 


from tensorflow.keras.utils import to_categorical
y = to_categorical(y)  # 원핫인코딩

x_train, x_test, y_train, y_test = train_test_split(
    x, y, shuffle=True,
    random_state=333,
    test_size= 0.2,
    stratify=y
)


scaler = MinMaxScaler()  # train을 기준으로 삼는다
scaler.fit(x_train)
x_train = scaler.transform(x_train)
# x_train = scaler.fit_transform(X_train)
x_test = scaler.transform(x_test)

print(x_train.shape, x_test.shape)  # (142, 13) (36, 13)
x_train = x_train.reshape(142, 13, 1)
x_test = x_test.reshape(36, 13, 1)
print(x_train.shape, x_test.shape)
# # #2. 모델 구성
model = Sequential()
model.add(Conv1D(64,2, input_shape=(13,1)))
model.add(Flatten())
model.add(Dense(64, activation='relu'))
model.add(Dropout(0.2))
model.add(Dense(32, activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dense(3, activation='softmax'))
model.summary()  

# #2. 모델 구성 (함수형)

# input1 = Input(shape=(13,))
# dense1 = Dense(50, activation='relu')(input1)
# drop1 = Dropout(0.5)(dense1)
# dense2 = Dense(40, activation='sigmoid')(drop1)
# drop2 = Dropout(0.5)(dense2)
# dense3 = Dense(50, activation='relu')(drop2)
# drop3 = Dropout(0.5)(dense3)
# dense4 = Dense(50, activation='linear')(drop3)
# output1 = Dense(3, activation='softmax')(dense4)

# model = Model(inputs=input1, outputs = output1)
# model.summary()  #4,611



#3. 컴파일, 훈련
from tensorflow.keras.callbacks import EarlyStopping 

earlystopping = EarlyStopping(monitor='val_loss',
                              mode='min', 
                              patience=350,
                              restore_best_weights=True,
                              verbose=1)

model.compile(loss='categorical_crossentropy', optimizer='adam',
              metrics=['accuracy'])
model.fit(x_train, y_train, epochs=200, batch_size=32,
          validation_split=0.2, callbacks=[earlystopping],
          verbose=3)

#4. 평가, 예측
loss, accuracy = model.evaluate(x_test, y_test)
print('loss :', loss)
print('accuracy :', accuracy)

# print(y_test[:5])
y_predict = model.predict(x_test[:5])
# print(y_predict)

y_predict = model.predict(x_test)
y_predict = np.argmax(y_predict, axis=1)   #axis=1 행에 있는것을 빼줌
print('y_pred(예측값) :', y_predict)
y_test = np.argmax(y_test, axis=1)
print('y_test(원래값) :', y_test)
acc = accuracy_score(y_test, y_predict)
print(acc)


#scaler 적용후 값   0.97222222