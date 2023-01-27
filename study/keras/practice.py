import numpy as np  
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, SimpleRNN, Dropout, LSTM
dataset = np.array(range(1,101))


timesteps = 5     # x = 4개 y = 1개

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
print (x.shape, y.shape) # (96,4)
x = x.reshape(97,4,1)
# x_predict = np.array(range(96,106))
print(x.shape)
model = Sequential()
model.add(LSTM(64, input_shape=(4, 1), activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dense(16, activation='relu'))
model.add(Dense(1))
model.summary()

# 컴파일 훈련
model.compile(loss='mse', optimizer='adam')
model.fit(x, y, epochs=2, batch_size=8, verbose=3)

# 평가, 예측
x_predict = np.array(range(96,106))  # 예상 y = 100, 107
loss = model.evaluate(x, y)
print('loss :', loss)
x_pred = x_predict.reshape.split_x(dataset, timesteps-1)
result =model.predict(x_pred)
print('[96,106]의 결과 :', result)