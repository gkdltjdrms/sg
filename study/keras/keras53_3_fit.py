import numpy as np 
from tensorflow.keras.preprocessing.image import ImageDataGenerator

#1 데이터

train_datagen = ImageDataGenerator(
    rescale=1./255,       # 각 픽셀 값을 255로 나누어 [0, 1] 범위로 다시 조정합니다.
    horizontal_flip=True, # 수평방향으로 뒤집기를 한다.
    vertical_flip=True,   # 수직방향으로 뒤집기를 한다. 
    width_shift_range=0.1, # 지정된 수평방향 이동 범위내에서 임의로 원본이미지를 이동시킨다.
    # 수치는 전체 넓이의 비율(실수)로 나타낸다. 예를 들어 0.1이고 전체 넓이가 100이면,
    # 10픽셀 내외로 좌우 이동시킨다. 
    height_shift_range=0.1,# 지정된 수직방향 이동 범위내에서 임의로 원본이미지를 이동시킨다.
    # 수치는 전체 높이의 비율(실수)로 나타낸다.
    # 예를 들어 0.1이고 전체 높이가 100이면, 10픽셀 내외로 상하 이동시킨다. 
    rotation_range= 5,  # 지정된 각도 범위내에서 임의로 원본이미지를 회전시킴. 단위는 도이며,
    # 정수형이다. 예를 들어 90이라면 0도에서 90도 사이에 임의의 각도로 회전시킨다. 
    zoom_range=1.2,# 지정된 확대/축소 범위내에서 임의로 원본이미지를 확대/축소한다. 
    # “1-수치”부터 “1+수치”사이 범위로 확대/축소를 한다.예를 들어 0.3이라면, 0.7배에서 1.3배 크기 변화를 시킨다. 
    shear_range=0.7, # 밀림 강도 범위내에서 임의로 원본이미지를 변형시킨다. 수치는 시계
    # 반대방향으로 밀림 강도를 라디안으로 나타낸다.
    # 예를 들어 0.5이라면, 0.5 라이안내외로 시계반대방향으로 변형시킨다. 
    fill_mode='nearest'  # 이미지의 정보 또는 데이터 손실을 방지하는 데 도움이 될 수 있습니다.
)

test_datagen = ImageDataGenerator(
    rescale=1./255
)

xy_train = train_datagen.flow_from_directory(
    './_data/brain/train', 
    target_size=(100, 100),
    batch_size=1000,
    class_mode='binary',
    color_mode='grayscale',
    shuffle=True,
     # Found 160 images belonging to 2 classes.
)
# train_datagen.flow_from_directory()
xy_test = train_datagen.flow_from_directory(
    './_data/brain/test', 
    target_size=(100, 100),
    batch_size=1000,
    class_mode='binary',
    color_mode='grayscale',
    shuffle=True,
     # Found 120 images belonging to 2 classes.
)
# batch_size = 1000 인 경우
print(xy_train[0][0].shape, xy_train[0][1].shape)#(160, 100, 100, 1) (160,) 앞에 0은 batch에 첫번째
print(xy_test[0][0].shape, xy_test[0][1].shape) #(120, 100, 100, 1) (120,)
# batch_size = 10 인 경우
print(xy_train[0][0].shape, xy_train[0][1].shape)# (10, 100, 100, 1) (10,)
print(xy_test[0][0].shape, xy_test[0][1].shape)# (10, 100, 100, 1) (10,)

# print(xy_train.imageshape)
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

# hist = model.fit_generator(xy_train,
#                     steps_per_epoch=10, #generator에서만 사용가능
#                     epochs=300,
#                     validation_data=xy_test,
#                     validation_steps=4,) #generator에서만 사용가능

hist = model.fit(xy_train[0][0],xy_train[0][1],  # (160, 100, 100, 1) (160,)
        #  steps_per_epoch=10,
        epochs=300,
        validation_data=(xy_test[0][0],xy_test[0][1])) #(120, 100, 100, 1) (120,)
        #  validation_steps=4,)

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


