
from tensorflow.keras.datasets import cifar10, cifar100
import numpy as np
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Conv2D, Dense, Flatten, Dropout, MaxPooling2D
import datetime 
from tensorflow.keras.callbacks import EarlyStopping,ModelCheckpoint
from sklearn.preprocessing import MinMaxScaler

#1. 데이터
(x_train, y_train), (x_test, y_test) = cifar100.load_data()


date = datetime.datetime.now()
date = date.strftime("%m%d_%H%M")
filepath = './_save/MCP/'
filename = '{epoch:04d}-{val_loss:.4f}.hdf5'  
path = './_save/'

#2. 모델 구성
model = Sequential() 
model.add(Conv2D(32, kernel_size=(3, 3), activation='relu', input_shape=(32, 32, 3)))
model.add(MaxPooling2D(pool_size=(2, 2)))
model.add(Dropout(0.25))
model.add(Flatten())
model.add(Dense(128, activation='relu'))
model.add(Dense(64, activation='relu'))
model.add(Dense(64, activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dropout(0.5))
model.add(Dense(100, activation='softmax'))

#3. 컴파일, 훈련

# mode= auto, min, max 
es = EarlyStopping(monitor='val_loss',
                              mode='auto', 
                              patience=100,
                              restore_best_weights=True,
                              verbose=1)

mcp = ModelCheckpoint(monitor='acc', mode='auto', verbose=1,
                      save_best_only=True,
                      filepath=filepath + 'k30_' + date+'_' + filename)


model.compile(loss='sparse_categorical_crossentropy', optimizer='adam', 
              metrics=['acc'])
model.fit(x_train, y_train, epochs=1500, verbose=1, batch_size=64,
          validation_split=0.2,
          callbacks=[es, mcp])

# model.save(path + 'keras34_ModelCheckPointcifar_save_model.h5')
#4. 평가, 예측
results = model.evaluate(x_test, y_test)
print('loss :', results[0])
print('acc :', results[1])



# patience 20 epo250 batch 1024 acc 0.41
