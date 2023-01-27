
from tensorflow.keras.datasets import cifar10, cifar100
import numpy as np
from tensorflow.keras.models import Sequential,Model
from tensorflow.keras.layers import Conv2D, Dense,Input,LSTM, Flatten, Dropout, MaxPooling2D
import datetime 
from tensorflow.keras.callbacks import EarlyStopping,ModelCheckpoint
from tensorflow.keras.utils import to_categorical
#1. 데이터
(x_train, y_train), (x_test, y_test) = cifar10.load_data()

print(x_train.shape, y_train.shape) 
print(x_test.shape, y_test.shape)

x_train = x_train.reshape(50000, 32*32*3)
x_test = x_test.reshape(10000, 32*32*3)  

x_test = x_test/255.
y_test = y_test/255.

# y_train = to_categorical(y_train)
# y_test = to_categorical(y_test)
print(x_train.shape, y_train.shape) # (50000, 32, 32, 3) 
print(x_test.shape, y_test.shape)   # (10000, 32, 32, 3) 



date = datetime.datetime.now()
date = date.strftime("%m%d_%H%M")
filepath = './_save/MCP/'
filename = '{epoch:04d}-{val_loss:.4f}.hdf5'  # 0037-0.0048.hdf5
path = './_save/'

# 2. 모델 구성
model = Sequential()
model.add(LSTM(64, activation='relu', input_shape=(32,3)))
model.add(Dense(32, activation='relu'))
model.add(Dropout(0.3))
model.add(Dense(32, activation='relu'))
model.add(Dense(64, activation='relu'))
model.add(Dropout(0.2))
model.add(Dense(64, activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dense(10, activation='softmax')) 
#



#3. 컴파일, 훈련

# mode= auto, min, max 
es = EarlyStopping(monitor='val_loss',
                              mode='auto', 
                              patience=20,
                              restore_best_weights=True,
                              verbose=1)

mcp = ModelCheckpoint(monitor='val_loss',
                      mode='auto',
                      verbose=1,
                      save_best_only=True,
                      filepath=filepath + 'k30_' + date+'_' + filename)


model.compile(loss='sparse_categorical_crossentropy', optimizer='adam', 
              metrics=['acc'])
model.fit(x_train, y_train, epochs=150, verbose=1, batch_size=512,
          validation_split=0.3,
          callbacks=[es, mcp])

# model.save(path + 'keras34_10_ModelCheckPointcifar_save_model.h5')
#4. 평가, 예측
results = model.evaluate(x_test, y_test)
print('loss :', results[0])
print('acc :', results[1])

## padding,maxpool acc:

