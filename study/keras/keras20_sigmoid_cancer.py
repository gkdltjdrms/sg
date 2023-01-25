import numpy as np
from sklearn.datasets import load_breast_cancer
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from sklearn.model_selection import train_test_split
from sklearn.metrics import classification_report
#1. 데이터
datasets = load_breast_cancer()
# print(datasets)
# print(datasets.DESCR)
# print(datasets.feature_names)

x = datasets['data']
y = datasets['target']
# print(x.shape, y.shape) # (569,30)   (569,)

x_train, x_test, y_train, y_test = train_test_split(x, y,
        shuffle=True, random_state=333, test_size=0.2)
#2. 모델 구성
model = Sequential()
model.add(Dense(50, activation='linear', input_shape=(30,)))
model.add(Dense(40, activation= 'relu'))
model.add(Dense(30, activation= 'relu'))
model.add(Dense(10, activation= 'relu'))
model.add(Dense(1, activation= 'sigmoid'))

#3. 컴파일, 훈련
model.compile(loss='binary_crossentropy', optimizer= 'adam',
              metrics=['accuracy'])

from tensorflow.keras.callbacks import EarlyStopping 
# mode= auto, min, max 
# earlystopping = EarlyStopping(monitor='val_loss',
earlystopping = EarlyStopping(monitor='accuracy',
                              mode='auto', 
                              patience=300,
                              restore_best_weights=True,
                              verbose=1)
hist = model.fit(x_train, y_train, epochs=10000, batch_size=16,
          validation_split=0.2,
          callbacks=[earlystopping],
          verbose=1)
# 2진분류는 binary_crossentropy
# BCE(x)=−1N∑i=1Nyilog(h(xi;θ))+(1−yi)log(1−h(xi;θ)) 

#4. 평가, 예측
# loss = model.evaluate(x_test, y_test)
# print('loss, accuracy :', loss)
loss, accuracy = model.evaluate(x_test, y_test)
print('loss :', loss)
print('accuracy :', accuracy)

y_predict = model.predict(x_test)

# preds_1d = y_predict.flatten() # 차원 펴주기
# pred_class = np.where(preds_1d > 0.5, 1 , 0) #0.5보다크면 1, 작으면 0



y_predict = y_predict.flatten()
y_predict = np.where(y_predict > 0.5, 1 , 0)


print(y_predict[:10])    # -> 정수형으로 바꿔야 한다.
print(y_test[:10])

from sklearn.metrics import r2_score, accuracy_score
# acc = accuracy_score(y_test, y_predict)
# print('accuracy_score :', acc)

print('========================')
print(hist.history)



