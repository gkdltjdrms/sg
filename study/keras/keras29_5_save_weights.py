import sklearn as sk
from tensorflow.keras.models import Sequential,Model,load_model
from tensorflow.keras.layers import Dense,Input
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.datasets import load_boston
from sklearn.preprocessing import MinMaxScaler, StandardScaler
from tensorflow.keras.callbacks import EarlyStopping 

path = './_save/'
# # path = '../_save/'
# # path = 'c:/study/_save/'
#!. 데이터
dataset = load_boston()
x = dataset.data
y = dataset.target

# scaler = MinMaxScaler()   # x-xmin / xmax-xmin   (x-mean)/std()

# x_train = scaler.transform(x_train)
# x_test = scaler.transform(x_test)

print(x)
print(type(x))  # <class 'numpy.ndarray'>
# print('최소값 :', np.min(x))
# print('최대값 :', np.max(x)) 

# print(x) 
# print(x.shape)  # (506, 13) 
# print(y)
# print(y.shape) #.(506,)n
x_train, x_test, y_train, y_test = train_test_split(x, y,
      train_size=0.7,
      shuffle=True,
      random_state=333                                                    
                                                    )

scaler = MinMaxScaler()  # train을 기준으로 삼는다
scaler.fit(x_train)
x_train = scaler.transform(x_train)
# x_train = scaler.fit_transform(X_train)
x_test = scaler.transform(x_test)


# x_train = scaler.transform(x_train)
# x_test = scaler.transform(x_test)

print(dataset.feature_names)
# ['CRIM' 'ZN' 'INDUS' 'CHAS' 'NOX' 
# 'RM' 'AGE' 'DIS' 'RAD' 'TAX' 'PTRATIO'
#  'B' 'LSTAT']

print(dataset.DESCR)

#2. 모델 구성 (함수형)

input1 = Input(shape=(13,))
dense1 = Dense(50, activation='relu')(input1)
dense2 = Dense(40, activation='sigmoid')(dense1)
dense3 = Dense(50, activation='relu')(dense2)
dense4 = Dense(50, activation='linear')(dense3)
output1 = Dense(1, activation='linear')(dense4)

model = Model(inputs=input1, outputs = output1)
model.summary()  #4,611

model.save_weights(path + 'keras29_5_save_weights1.h5')

# model.save(path + 'keras29_1_save_model.h5')
# model.save('./_save/keras29_save_model.h5')

# #3. 컴파일. 훈련
# import time

model.compile(loss='mse', optimizer='adam',
              metrics=['mae'])
earlystopping = EarlyStopping(monitor='val_loss',
                              mode='min', 
                              patience=10,
                              restore_best_weights=True,
                              verbose=1)
# start=time.time()

hist = model.fit(x_train, y_train, epochs=100, batch_size=32,
          validation_split=0.2,
          callbacks=[earlystopping],
          verbose=3)

model.save_weights(path + 'keras29_5_save_weights2.h5')



# # end=time.time()


#4. 평가, 예측
loss = model.evaluate(x_test, y_test)
print('loss :',  loss)

y_predict = model.predict(x_test)

from sklearn.metrics import mean_squared_error, r2_score
def RMSE(y_test, y_predict):
    return np.sqrt(mean_squared_error(y_test, y_predict))

print('RMSE :', RMSE(y_test, y_predict))

r2 =  r2_score(y_test, y_predict)
print('R2 :', r2)

# print('걸린시간 :', end - start)
# minmax r2: 0.78234118884049
# standard scaler r2: 0.8081614875403266





