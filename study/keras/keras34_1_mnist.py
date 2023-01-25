import numpy as np
from tensorflow.keras.datasets import mnist
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Conv2D, Dense, Flatten
import datetime 
from tensorflow.keras.callbacks import EarlyStopping,ModelCheckpoint
#1. 데이터
(x_train, y_train), (X_test, y_test) = mnist.load_data()

print(x_train.shape, y_train.shape) # (60000, 28, 28) (60000,)
print(X_test.shape, y_test.shape) # (10000, 28, 28) (10000,)

x_train = x_train.reshape(60000, 28, 28, 1)
X_test = X_test.reshape(10000, 28, 28, 1)

print(x_train.shape, y_train.shape) # (60000, 28, 28, 1) 
print(X_test.shape, y_test.shape)   # (10000, 28, 28, 1)

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
#2. 모델 구성
model = Sequential()
model.add(Conv2D(filters= 128,kernel_size=(2,2), input_shape=(28,28,1),
        activation='relu'))                       # (27, 27, 128)
model.add(Conv2D(filters=64, kernel_size=(2,2)))  # (26, 26, 64)
model.add(Conv2D(filters=64, kernel_size=(2,2)))  # (25, 25, 64) > 40000
model.add(Flatten())   #  - > 40000
model.add(Dense(32, activation='relu'))  # input_shape = (40000,)
                                         # (60000, 40000)    (batch_size, input_dim)
model.add(Dense(10, activation='softmax')) 


#3. 컴파일, 훈련

# mode= auto, min, max 
es = EarlyStopping(monitor='val_loss',
                              mode='min', 
                              patience=30,
                              restore_best_weights=True,
                              verbose=1)

mcp = ModelCheckpoint(monitor='val_loss', mode='auto', verbose=1,
                      save_best_only=True,
                      filepath=filepath + 'k30_' + date+'_' + filename)


model.compile(loss='sparse_categorical_crossentropy', optimizer='adam', 
              metrics=['acc'])
model.fit(x_train, y_train, epochs=500, verbose=3, batch_size=32,
          validation_split=0.2,
          callbacks=[es, mcp])

model.save(path + 'keras34_ModelCheckPoint3_save_model.h5')

#4. 평가, 예측
results = model.evaluate(X_test, y_test)
print('loss :', results[0])
print('acc :', results[1])


#es, mcp 적용 /val 적용

