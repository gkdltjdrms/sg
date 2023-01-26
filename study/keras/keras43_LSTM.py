import numpy as np 
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, SimpleRNN, Dropout, LSTM

#1. 데이터
dataset = np.array([1,2,3,4,5,6,7,8,9,10]) # (10, )
# y = ??

x = np.array([[1,2,3],
              [2,3,4],
              [3,4,5],
              [4,5,6],
              [5,6,7],
              [6,7,8],
              [7,8,9]])

y = np.array([4,5,6,7,8,9,10])

print(x.shape, y.shape) # (7, 3), (7, ) 

x = x.reshape(7, 3, 1)
print(x.shape)    #(7, 3, 1)
# 모델구성
model = Sequential()
# model.add(SimpleRNN(units = 64, input_shape=(3, 1)))
                 # (n, 3, 1) -> ([batch, timesteps, feature]) 시계열 데이터에는 y 가 없다
model.add(LSTM(units = 10, input_shape=(3, 1)))
model.add(Dense(5, activation='relu'))
model.add(Dense(1))
 
model.summary() # 64*64+(64*1(input_shape))+64
                # units * ( feature + bios + units ) = params

#심플  10*(10+1+1)
#LSTM 심플rnn * 4  이유:3개의 게이트와 한개의 스테이트(cellstate)

