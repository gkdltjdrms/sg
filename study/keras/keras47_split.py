import numpy as np    
import numpy as np                       
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, SimpleRNN, Dropout, LSTM
dataset = np.array(range(1, 11))
timesteps = 5

def split_x(dataset, timesteps):
    aaa = []
    for i in range(len(dataset) - timesteps + 1):
        subset = dataset[i : (i + timesteps)]
        aaa.append(subset)
    return np.array(aaa)
    
bbb = split_x(dataset, timesteps)
print(bbb, bbb.shape)

x = bbb[:, :-1]
y = bbb[:, -1]
print (x,y)
print (x.shape, y.shape) # (6, 4) (6,)
x = x.reshape(6,4,1)
x_predict = np.array([7,8,9,10])
# 실습  
# LSTM 모델구성
model = Sequential()
model.add(LSTM(64, input_shape=(4, 1), activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dense(16, activation='relu'))
model.add(Dense(1))
model.summary()

# 컴파일 훈련
model.compile(loss='mse', optimizer='adam')
model.fit(x, y, epochs=250, batch_size=2)

# 평가, 예측
loss = model.evaluate(x, y)
print('loss :', loss)
x_pred = x_predict.reshape(1, 4, 1)
result =model.predict(x_pred)
print('[7,8,9,10]의 결과 :', result)