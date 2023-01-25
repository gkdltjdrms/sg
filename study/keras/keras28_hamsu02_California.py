# [실습]
# R2 : 0.55~0.6 이상 만들어보기
import sklearn as sk
from tensorflow.keras.models import Sequential,Model
from tensorflow.keras.layers import Dense,Input
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import MinMaxScaler, StandardScaler
from tensorflow.keras.callbacks import EarlyStopping 


from sklearn.datasets import fetch_california_housing

#1. 데이터
datasets = fetch_california_housing()
x = datasets.data
y = datasets.target


# print(x)
# print(x.shape)   # (20640, 8)
# print(y)
# print(y.shape)   # (20640, )

x_train, x_test, y_train, y_test = train_test_split(x, y,
      train_size=0.7,
      shuffle=True,
      random_state=111                                                    
                                                    )

scaler = MinMaxScaler()  # train을 기준으로 삼는다
scaler.fit(x_train)
x_train = scaler.transform(x_train)
# x_train = scaler.fit_transform(X_train)
x_test = scaler.transform(x_test)

#2. 모델 구성 (순차형)
# model = Sequential()
# model.add(Dense(30, input_dim=8, activation='relu'))
# model.add(Dense(50, activation='relu'))
# model.add(Dense(100, activation='relu'))
# model.add(Dense(200, activation='relu'))
# model.add(Dense(150, activation='relu'))
# model.add(Dense(130, activation='relu'))
# model.add(Dense(75, activation='relu'))
# model.add(Dense(1))

#2. 모델 구성 (함수형)

input1 = Input(shape=(8,))
dense1 = Dense(50, activation='relu')(input1)
dense2 = Dense(40, activation='sigmoid')(dense1)
dense3 = Dense(50, activation='relu')(dense2)
dense4 = Dense(50, activation='linear')(dense3)
output1 = Dense(1, activation='linear')(dense4)

model = Model(inputs=input1, outputs = output1)
model.summary()  #4,611


import time


model.compile(loss='mse', optimizer='adam',
              metrics=['mse'])
earlystopping = EarlyStopping(monitor='val_loss',
                              mode='min', 
                              patience=150,
                              restore_best_weights=True,
                              verbose=1)
start = time.time()
hist = model.fit(x_train, y_train, epochs=30, batch_size=100,
          validation_split=0.2,
          callbacks=[earlystopping],
          verbose=3)
end = time.time()

loss = model.evaluate(x_test, y_test)
print('loss :',  loss)


y_predict = model.predict(x_test)
from sklearn.metrics import mean_squared_error, r2_score
def RMSE(y_test, y_predict):
    return np.sqrt(mean_squared_error(y_test, y_predict))

print('RMSE :', RMSE(y_test, y_predict))

r2 =  r2_score(y_test, y_predict)
print('R2 :', r2)

print('걸린시간 :',  end - start)

#gpu 걸린시간 39.2074589729
#gpu 걸린시간: 27.21
#cpu 걸린시간 19.8047
#cpu 걸린시간 16.540

#loss : [0.5511970520019531, 0.5511970520019531]
# RMSE : 0.7424264769070442
# R2 : 0.5831493444239657
#  cpu 걸린시간 : 5.9094358444214
# gpu 걸린시간 :9.3 

#0.55
#0.60
#0.62


# scaler r2 : 0.79
