import tensorflow as tf 
print(tf.__version__)  # 2.7.4

gpus = tf.config.experimental.list_physical_devices('GPU') # 테스트 버젼
print(gpus)  # [PhysicalDevice
# (name='/physical_device:GPU:0', device_type='GPU')]
if(gpus):
    print('쥐피유 돈다')
else:
    print('쥐피유 안돈다')
    
#3333333
 
#분류                      회귀,                   이진,                          다중
#loss                    mse mae ...       binary_crossentropy      categorical_crossentropy,sparse
#아웃풋 (노드)          y의 컬럼 갯수만큼              1                    원핫한 클래스의 갯수 만큼 
#아웃풋(activation)       linear                 sigmoid                         softmax
#원핫                       x                       ▲                             0 / x
 
