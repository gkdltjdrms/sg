import numpy as np  
from sklearn.datasets import fetch_covtype
from tensorflow.keras.models import Sequential,Model,load_model
from tensorflow.keras.layers import Dense,Conv1D,Input, Dropout, Conv2D,LSTM, Flatten
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score
import pandas as pd
import time
import tensorflow as tf
from tensorflow.keras.utils import to_categorical
from sklearn.preprocessing import MinMaxScaler, StandardScaler
# categorical 을 사용할시 컬럼 삭제
# https://chat.openai.com/chat

#1. 데이터
datasets = fetch_covtype()  
x = datasets.data
y = datasets['target']

#3. onehotencoder===========================================
from sklearn.preprocessing import OneHotEncoder
print(y.shape)
y = y.reshape(581012, 1)   # reshape는 데이터의 내용물이 바뀌면 안된다
print(y.shape)
ohe = OneHotEncoder()
y = ohe.fit_transform(y)
y=y.toarray()

#---------------------------------------------------------------

# # ohe.fit(y)
# # y = ohe.transform     # y = ohe.fit_transform(y) 한줄로 줄일수 있다
# print(y[:15])
# print(type(y))
# print(y.shape)

# y = y.reshape(-1, 1) 


# y=y.toarray() sparse = False: array 반환 
# (one-hot encoding에 필요한것은 array이므로)

# ===========================================================
# print(y.shape)
# print(y.shape)
# print(type(y))
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
# # print(x.shape, y.shape) #(581012, 54) (581012, 8) x// (581012, 54) (581012, 7)
print(x_train.shape, x_test.shape)  # (464809, 54) (116203, 54)
x_train = x_train.reshape(464809, 54, 1, 1)
x_test = x_test.reshape(116203, 54, 1, 1)
##=======================================================
import datetime 
date = datetime.datetime.now()
print(date)
print(type(date)) # <class 'datetime.datetime'>  문자열 형태로 변경
date = date.strftime("%m%d_%H%M")
print(date)    # 0112_1502
print(type(date))  # <calss 'str'>

filepath = './_save/MCP/'
filename = '{epoch:04d}-{val_loss:.4f}.hdf5'  # 0037-0.0048.hdf5
path = './_save/'
##==================================================

#2. 모델 구성
# model = Sequential()
model = Sequential()
model.add(Conv1D(64,2,  input_shape=(54,1)))
model.add(Flatten())
model.add(Dense(64, activation='relu'))
model.add(Dropout(0.2))
model.add(Dense(32, activation='relu'))
model.add(Dropout(0.4))
model.add(Dense(32, activation='relu'))
model.add(Dense(7, activation='softmax'))





#3. 컴파일, 훈련
from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint 

es = EarlyStopping(monitor='val_loss',
                              mode='min', 
                              patience=50,
                              restore_best_weights=True,
                              verbose=1)
mcp = ModelCheckpoint(monitor='val_loss', mode='auto', verbose=1,
                      save_best_only=True,
                      filepath=filepath + 'k31_01_' + date+'_' + filename)


model.compile(loss='categorical_crossentropy', optimizer='adam',
              metrics=['accuracy'])
start = time.time()

model.fit(x_train, y_train, epochs=3, batch_size=512,  
          validation_split=0.2, callbacks=[es],
          verbose=3)
end = time.time()
#4. 평가, 예측
loss, accuracy = model.evaluate(x_test, y_test)
print('loss :', loss)
print('accuracy :', accuracy)

y_predict = model.predict(x_test[:5])
# print(y_predict)


y_predict = model.predict(x_test)
y_predict = np.argmax(y_predict, axis=1)   #axis=1 행에 있는것을 빼줌
print('y_pred(예측값) :', y_predict[:20])
y_test = np.argmax(y_test, axis=1)
print('y_test(원래값) :', y_test[:20])
acc = accuracy_score(y_test, y_predict)
print('acc :', acc)
print('걸린시간 :', end - start)

# 다중분류에서는 원핫인코딩을 한다

#  scaler acc : 0.9177043621937472
# dropout acc : 0.77













