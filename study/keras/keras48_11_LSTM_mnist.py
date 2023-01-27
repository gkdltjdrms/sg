import numpy as np
from tensorflow.keras.datasets import mnist
from tensorflow.keras.models import Sequential, Model
from tensorflow.keras.layers import Conv2D, Dense,LSTM, Flatten, MaxPooling2D, Dropout, Input
import datetime 
from tensorflow.keras.callbacks import EarlyStopping,ModelCheckpoint
#1. 데이터
(x_train, y_train), (x_test, y_test) = mnist.load_data()

print(x_train.shape, y_train.shape) # (60000, 28, 28) (60000,)
print(x_test.shape, y_test.shape) # (10000, 28, 28) (10000,)

# x_train = x_train.reshape(60000, 28, 28, 1)
# X_test = X_test.reshape(10000, 28, 28, 1)

x_train = x_train.reshape(60000, 28*28)
x_test = x_test.reshape(10000, 28*28)

x_test = x_test/255.0
y_test = y_test/255.0

print(x_train.shape, y_train.shape) # (60000, 28, 28, 1) 
print(x_test.shape, y_test.shape)   # (10000, 28, 28, 1)

print(np.unique(y_train, return_counts=True))


date = datetime.datetime.now()
print(date)
print(type(date)) # <class 'datetime.datetime'>  문자열 형태로 변경
date = date.strftime("%m%d_%H%M")
print(date)    # 0112_1502
print(type(date))  # <calss 'str'>

filepath = './_save/MCP/'
filename = '{epoch:04d}-{val_loss:.4f}.hdf5'  # 0037-0.0048.hdf5
path = './_save/'
# 2. 모델 구성  #784
model = Sequential()
model.add(LSTM(32, activation='relu', input_shape=(784,1)))
model.add(Dropout(0.3))
model.add(Dense(64, activation='relu'))
model.add(Dropout(0.3))
model.add(Dense(64, activation='linear'))
model.add(Dense(10, activation='softmax')) 

# model.summary()



#3. 컴파일, 훈련

# mode= auto, min, max 
es = EarlyStopping(monitor='acc',
                              mode='max', 
                              patience=60,
                              restore_best_weights=True,
                              verbose=1)

mcp = ModelCheckpoint(monitor='val_loss', mode='auto', verbose=1,
                      save_best_only=True,
                      filepath=filepath + 'k30_' + date+'_' + filename)


model.compile(loss='sparse_categorical_crossentropy', optimizer='adam', 
              metrics=['acc'])
model.fit(x_train, y_train, epochs=10, verbose=1, batch_size=512,
          validation_split=0.2,
          callbacks=[es, mcp])

model.save(path + 'keras34_ModelCheckPoint3_save_model.h5')

#4. 평가, 예측
results = model.evaluate(x_test, y_test)
print('loss :', results[0])
print('acc :', results[1])


#es, mcp 적용 /val 적용
# acc 

#padding 적용후 0.9739

#Maxpool 적용시... 0.98
