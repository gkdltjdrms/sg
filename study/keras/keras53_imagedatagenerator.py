import numpy as np 
from tensorflow.keras.preprocessing.image import ImageDataGenerator

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
    target_size=(200, 200),
    batch_size=10,
    class_mode='binary',
    color_mode='grayscale',
    shuffle=True,
     # Found 160 images belonging to 2 classes.
)
# train_datagen.flow_from_directory()
xy_test = train_datagen.flow_from_directory(
    './_data/brain/test', 
    target_size=(200, 200),
    batch_size=10,
    class_mode='binary',
    color_mode='grayscale',
    shuffle=True,
     # Found 120 images belonging to 2 classes.
)

print(xy_train) #<keras.preprocessing.image.DirectoryIterator 
# object at 0x000001E82599C0A0>

print (xy_train[0][0].shape)  # (10, 200, 200, 10)
print (xy_train[0][1].shape)  # (10,)
# batch_size 를 크게 잡았을때 데이터의 개수를 확인할수 있다.
print (type(xy_train[0])) # <class 'tuple'>
print(type(xy_train[0][0]))# <class 'numpy.ndarray'>
print(type(xy_train[0][1]))# <class 'numpy.ndarray'>





