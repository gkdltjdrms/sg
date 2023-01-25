
from tensorflow.keras.datasets import cifar10, cifar100
import numpy as np
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Conv2D, Dense, Flatten, Dropout, MaxPooling2D
import datetime 
from tensorflow.keras.callbacks import EarlyStopping,ModelCheckpoint
#1. 데이터
(x_train, y_train), (X_test, y_test) = cifar10.load_data()

# # print(x_train.shape, y_train.shape) # (50000, 32, 32, 3) (50000, 1)
# print(X_test.shape, y_test.shape) # (10000, 32, 32, 3) (10000,)
# print(x_train.shape, y_train.shape)  
# print(X_test.shape, y_test.shape)   

# print(np.unique(y_train, return_counts=True))

date = datetime.datetime.now()
date = date.strftime("%m%d_%H%M")
filepath = './_save/MCP/'
filename = '{epoch:04d}-{val_loss:.4f}.hdf5'  # 0037-0.0048.hdf5
path = './_save/'

#2. 모델 구성
model = Sequential()
model.add(Conv2D(filters= 128,kernel_size=(2,2), input_shape=(32,32,3),
                 padding='same',
                 activation='relu'))                       # (31, 31, 128)
model.add(Conv2D(32, kernel_size=(3, 3), activation='relu', input_shape=(32, 32, 3)))
model.add(MaxPooling2D(pool_size=(2, 2)))
model.add(Dropout(0.25))
model.add(Flatten())
model.add(Dense(128, activation='relu'))
model.add(Dense(64, activation='relu'))
model.add(Dense(64, activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dropout(0.5))
model.add(Dense(10, activation='softmax'))

#3. 컴파일, 훈련

# mode= auto, min, max 
es = EarlyStopping(monitor='acc',
                              mode='auto', 
                              patience=250,
                              restore_best_weights=True,
                              verbose=1)

mcp = ModelCheckpoint(monitor='val_loss', mode='auto', verbose=1,
                      save_best_only=True,
                      filepath=filepath + 'k30_' + date+'_' + filename)


model.compile(loss='sparse_categorical_crossentropy', optimizer='adam', 
              metrics=['acc'])
model.fit(x_train, y_train, epochs=350, verbose=1, batch_size=2048,
          validation_split=0.3,
          callbacks=[es, mcp])

model.save(path + 'keras34_10_ModelCheckPointcifar_save_model.h5')
#4. 평가, 예측
results = model.evaluate(X_test, y_test)
print('loss :', results[0])
print('acc :', results[1])

## padding,maxpool acc:

