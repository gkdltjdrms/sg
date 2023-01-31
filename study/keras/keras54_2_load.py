import numpy as np 
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense,Conv2D,Flatten

# # batch_size 를 크게 잡았을때 데이터의 개수를 확인할수 있다.
# print (type(xy_train[0])) # <class 'tuple'>
# print(type(xy_train[0][0]))# <class 'numpy.ndarray'>
# print(type(xy_train[0][1]))# <class 'numpy.ndarray'>

x_train = np.load('./_data/brain/x_train.npy')
y_train = np.load('./_data/brain/y_train.npy')
x_test = np.load('./_data/brain/x_test.npy')
y_test = np.load('./_data/brain/y_test.npy')

print(x_train.shape,y_test.shape)
print(y_train.shape,x_test.shape)

model = Sequential()
model.add(Conv2D(32, (2,2), input_shape = (200, 200, 1)))
model.add(Conv2D(16,(3,3), activation='relu'))
model.add(Conv2D(16,(3,3), activation='relu'))
model.add(Flatten())
model.add(Dense(32, activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dense(16, activation='relu'))
model.add(Dense(16, activation='relu'))
model.add(Dense(16, activation='relu'))
# model.add(Dense(1, activation='sigmoid'))
model.add(Dense(2, activation='softmax'))
model.summary()

#3 컴파일 훈련

# model.compile(loss='binary_crossentropy', optimizer='adam',
#               metrics=['acc'])

model.compile(loss='sparse_categorical_crossentropy', optimizer='adam',
              metrics=['acc'])  # softmax , 원핫인코딩을 sparse로 

hist = model.fit_generator(x_train,y_train,
                    # steps_per_epoch=10, #generator에서만 사용가능 
                    epochs=300,
                #     validation_data=x_test,
                    validation_data=x_test,
                    validation_steps=4,) #generator에서만 사용가능

# hist = model.fit(x_train,y_train,  # (160, 100, 100, 1) (160,)
#         #  steps_per_epoch=10,
#         epochs=300,
#         validation_split=0.2)
#         # validation_data=(x_test,y_test)) #(120, 100, 100, 1) (120,)

#         #  validation_steps=4,)

acc = hist.history['acc']
val_acc = hist.history['val_acc']
loss = hist.history['loss']
val_loss = hist.history['val_loss']

print ('loss :',loss[-1])
print ('val_loss :', val_loss[-1])
print ('acc :',acc[-1])
print ('val_acc :', val_acc[-1])

#
# loss : 1.0475424971900793e-07
# val_loss : 4.4136857986450195
# acc : 1.0
# val_acc : 0.5916666388511658
