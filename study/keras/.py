from sklearn.datasets import load_iris
from tensorflow.keras.models import Sequential,Model
from tensorflow.keras.layers import Dense,Input,LSTM, Dropout, Conv2D, Flatten
from sklearn.model_selection import train_test_split
import tensorflow as tf
from sklearn.preprocessing import MinMaxScaler, StandardScaler

# from tensorflow.keras.utils import to_categorical 
# or tensorflow as tf 변환하여 one_shot 으로 변환

##
#1. 데이터
datasets = load_iris()
# datasets = load_iris()
# print(datasets.DESCR)              #판다스   .describe() / .info()
# print(datasets.feature_names)      #판다스   .colums

x = datasets.data
y = datasets['target']

from tensorflow.keras.utils import to_categorical


# y = to_categorical(y)  # 원핫인코딩
# #------------------------------------------------------
# # print(x)
# # print(y)
# # print(x.shape, y.shape)   # (150, 4)   (150,) -> one hot (150, 3)
                                   


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


print(x_train.shape, x_test.shape)  # (120, 4) (30, 4)

# 데이터에 따라 shuffle false일시 문제 발생 분류에서만 문제가 생김
# print(y_train)
# print(y_test)

#2. 모델 구성
model = Sequential()
model.add(LSTM(64, input_shape=(4,1)))
model.add(Flatten())
model.add(Dense(64, activation='relu'))
model.add(Dropout(0.2))
model.add(Dense(32, activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dense(1))
model.summary()  
# # #2. 모델 구성 (함수형)

# input1 = Input(shape=(4,))
# dense1 = Dense(50, activation='relu')(input1)
# dense2 = Dense(40, activation='sigmoid')(dense1)
# dense3 = Dense(50, activation='relu')(dense2)
# dense4 = Dense(50, activation='linear')(dense3)
# output1 = Dense(3, activation='softmax')(dense4)
# model = Model(inputs=input1, outputs = output1)
# model.summary()  #4,611



#3. 컴파일, 훈련
model.compile(loss='sparse_categorical_crossentropy', optimizer='adam',
              metrics=['accuracy'])
model.fit(x_train, y_train, epochs=500, batch_size=64,
          validation_split=0.2,
          verbose=3)
# one hot encoding

#4. 평가, 예측
loss, accuracy = model.evaluate(x_test, y_test)
print('loss :', loss)
print('accuracy :', accuracy)

print(y_test[:5])
y_predict = model.predict(x_test[:5])
print(y_predict)

from sklearn.metrics import accuracy_score
import numpy as np
    
y_predict = model.predict(x_test)
y_predict = np.argmax(y_predict, axis=1)   #axis=1 행에 있는것을 빼줌
print('y_pred(예측값) :', y_predict)
y_test = np.argmax(y_test, axis=1)
print('y_test(원래값) :', y_test)
acc = accuracy_score(y_test, y_predict)
print(acc)


# scaler acc : 0.9333333333333333



##
