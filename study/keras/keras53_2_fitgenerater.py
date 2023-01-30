import numpy as np 
from tensorflow.keras.preprocessing.image import ImageDataGenerator

#1 데이터

train_datagen = ImageDataGenerator(
    rescale=1./255,
    horizontal_flip=True,
    vertical_flip=True,
    width_shift_range=0.1,
    height_shift_range=0.1,
    rotation_range= 5,
    zoom_range=1.2,
    shear_range=0.7, 
     fill_mode='nearest'
)

test_datagen = ImageDataGenerator(
    rescale=1./255
)

xy_train = train_datagen.flow_from_directory(
    './_data/brain/train', 
    target_size=(100, 100),
    batch_size=10,
    class_mode='binary',
    color_mode='grayscale',
    shuffle=True,
     # Found 160 images belonging to 2 classes.
)
# train_datagen.flow_from_directory()
xy_test = train_datagen.flow_from_directory(
    './_data/brain/test', 
    target_size=(100, 100),
    batch_size=10,
    class_mode='binary',
    color_mode='grayscale',
    shuffle=True,
     # Found 120 images belonging to 2 classes.
)

#2 모델 구성
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense,Conv2D,Flatten

model = Sequential()
model.add(Conv2D(64, (2,2), input_shape = (100, 100, 1)))
model.add(Conv2D(64,(3,3), activation='relu'))
model.add(Conv2D(64,(3,3), activation='relu'))
model.add(Flatten())
model.add(Dense(32, activation='relu'))
model.add(Dense(64, activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dense(1, activation='sigmoid'))
model.summary()

#3 컴파일 훈련

model.compile(loss='binary_crossentropy', optimizer='adam',
              metrics=['acc'])

model.fit_generator(xy_train, steps_per_epoch=10, epochs=500,
                    validation_data=xy_test,
                    validation_steps=4, )
loss = model.evaluate(xy_test)
print ('loss, acc :', loss)




