import numpy as np
from sklearn.datasets import load_digits
from tensorflow.keras.models import Sequential,Model
from tensorflow.keras.layers import Dense,Conv1D,Input, Dropout,LSTM, Conv2D, Flatten
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score
from sklearn.preprocessing import MinMaxScaler, StandardScaler
#1. 데이터
datasets = load_digits()
x = datasets.data
y = datasets['target']
print(x.shape, y.shape) # (1797, 64) (1797,)
print(np.unique(y, return_counts=True))
# (array([0, 1, 2, 3, 4, 5, 6, 7, 8, 9]),
# array([178, 182, 177, 183, 181, 182, 181, 179, 174, 180], dtype=int64

import matplotlib.pyplot as plt
# plt.gray()
# plt.matshow(datasets.images[4])
# plt.show()

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

print(x_train.shape, x_test.shape) # (1437, 64) (360, 64)
x_train = x_train.reshape(1437, 64, 1, 1)
x_test = x_test.reshape(360, 64, 1, 1)
print(x_train.shape, x_test.shape)
# #2. 모델구성

# model = Sequential()
model = Sequential()
model.add(Conv1D(64,2, input_shape=(64,1)))
model.add(Flatten())
model.add(Dense(64, activation='relu'))
model.add(Dropout(0.2))
model.add(Dense(32, activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dense(10, activation='softmax'))


#3. 컴파일, 훈련

from tensorflow.keras.callbacks import EarlyStopping, ModelCheckpoint 
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
model.fit(x_train, y_train, epochs=100, batch_size=32,
          validation_split=0.2, callbacks=[es],
          verbose=3)
print (x.shape, y.shape)
#4. 평가, 예측

loss, accuracy = model.evaluate(x_test, y_test)
print('loss :', loss)
print('accuracy :', accuracy)

y_predict = model.predict(x_test[:5])

y_predict = model.predict(x_test)
y_predict = np.argmax(y_predict, axis=1)   #axis=1 행에 있는것을 빼줌
# print('y_pred(예측값) :', y_predict)
y_test = np.argmax(y_test, axis=1)
# print('y_test(원래값) :', y_test)
acc = accuracy_score(y_test, y_predict)
print(acc)
#  scaler 적용후 값 0.97777777
