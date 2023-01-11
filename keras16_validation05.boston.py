# [실습]
# 1. train 0.7 이상
# 2. R2 : 0.8 이상 / RMSE사용
# 평가지표: R2, RMSE
import sklearn as sk
# print(sk.__version__)  #1.1.3

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
import numpy as np
from sklearn.model_selection import train_test_split
from sklearn.datasets import load_boston
#!. 데이터
dataset = load_boston()
x = dataset.data
y = dataset.target

print(x) 
print(x.shape)  # (506, 13) 
print(y)
print(y.shape) #.(506,)

x_train, x_test, y_train, y_test = train_test_split(x, y,
      train_size=0.7,
      shuffle=True,
      random_state=123                                                    
                                                    )



print(dataset.feature_names)
# ['CRIM' 'ZN' 'INDUS' 'CHAS' 'NOX' 
# 'RM' 'AGE' 'DIS' 'RAD' 'TAX' 'PTRATIO'
#  'B' 'LSTAT']

print(dataset.DESCR)

#2. 모델 구성
model = Sequential()
model.add(Dense(6, input_dim=13))
model.add(Dense(32, activation='relu'))
model.add(Dense(100, activation='relu'))
model.add(Dense(300, activation='relu'))
model.add(Dense(250, activation='relu'))
model.add(Dense(80, activation='relu'))
model.add(Dense(50, activation='relu'))
model.add(Dense(1))

#3. 컴파일. 훈련
import time

model.compile(loss='mse', optimizer='adam',
              metrics=['mse'])
# start=time.time()

model.fit(x_train, y_train, epochs= 1000, batch_size=50,
          validation_split=0.7)

# end=time.time()
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



























