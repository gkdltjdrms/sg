import numpy as np 
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense,Conv2D,Flatten,Dropout,MaxPooling2D
from sklearn.model_selection import train_test_split

# # batch_size 를 크게 잡았을때 데이터의 개수를 확인할수 있다.
# print (type(xy_train[0])) # <class 'tuple'>
# print(type(xy_train[0][0]))# <class 'numpy.ndarray'>
# print(type(xy_train[0][1]))# <class 'numpy.ndarray'>

x_train = np.load('d:./_data/catdog/x_train.npy')
y_train = np.load('d:./_data/catdog/y_train.npy')
x_test = np.load('d:./_data/catdog/x_test.npy')
y_test = np.load('d:./_data/catdog/y_test.npy')
y = np.load('d:./_data/catdog/y4_predict.npy')

print(x_train.shape)  #(25000, 200, 200, 3)
print(y_train.shape)  #(25000, )
print(x_test.shape)  #(10000,200,200,3)
print(y_test.shape)  #(10000,)
model = Sequential()
model.add(Conv2D(64, (3,3), input_shape=(200,200,3), activation='relu', padding='same'))
model.add(MaxPooling2D())
model.add(Conv2D(32, (2,2), activation='relu', padding='same'))
model.add(Conv2D(16, (2,2), activation='relu', padding='same'))
model.add(Conv2D(16, (2,2), activation='relu', padding='same'))
model.add(MaxPooling2D())
model.add(Conv2D(8, (2,2), activation='relu'))
model.add(Flatten())
model.add(Dense(64, activation='relu'))
model.add(Dense(64, activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dense(16, activation='relu'))
model.add(Dense(1, activation='sigmoid'))

#3 컴파일 훈련

model.compile(loss='binary_crossentropy', optimizer='adam',
              metrics=['acc'])

# model.compile(loss='sparse_categorical_crossentropy', optimizer='adam',
#               metrics=['acc'])  # softmax , 원핫인코딩을 sparse로 

# model.compile(loss='binary_crossentropy', optimizer='adam',
#               metrics=['acc'])



# hist = model.fit_generator(x_train,y_train,
#                     steps_per_epoch=10, #generator에서만 사용가능 
#                     epochs=300,
#                     # validation_data=x_test,
#                     validation_data=x_test,
#                     validation_steps=4,) #generator에서만 사용가능




hist = model.fit(x_train,y_train,  
        #  steps_per_epoch=10,
        epochs=30,
        validation_data=(x_test, y_test)) 
        # validation_split=0.25
         
        # validation_data=(x_test,y_test)) #(120, 100, 100, 1) (120,)


acc = hist.history['acc']
val_acc = hist.history['val_acc']
loss = hist.history['loss']
val_loss = hist.history['val_loss']

print ('loss :',loss[-1])
print ('val_loss :', val_loss[-1])
print ('acc :',acc[-1])
print ('val_acc :', val_acc[-1])

y_pred = model.predict(y)
y_pred = (y_pred > 0.5).astype(int)
print('Prediction:', y_pred)
#개          0.999  0.999   y3강아지 0.005
#고양이      5.0    y4고양이 0.97