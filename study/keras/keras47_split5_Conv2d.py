import numpy as np  
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense,Conv2D,Flatten, SimpleRNN, Dropout, LSTM
from sklearn.model_selection import train_test_split
dataset = np.array(range(1,101))
x_predict = np.array(range(96,106))  # 예상 y = 100, 107

timesteps = 5     

def split_x(dataset, timesteps):
    aaa = []
    for i in range(len(dataset) - timesteps + 1):
        subset = dataset[i : (i + timesteps)]
        aaa.append(subset)
    return np.array(aaa)


bbb = split_x(dataset, timesteps)


x = bbb[:, :-1]
y = bbb[:, -1]
x_predict = split_x(x_predict, 4)  # dataset , timestpes  
# x_predict = x_predict.reshape(7, 4, 1)

x_train, x_test, y_train, y_test = train_test_split(x, y,
      shuffle=True,
      train_size=0.75,
      random_state=123)


x_train = x_train.reshape(72,2,2,1)   # 72,1,4 를 72,2,2
x_test = x_test.reshape(24,2,2,1)
x_predict = x_predict.reshape(7,2,2,1)



model = Sequential()
model.add(Conv2D(64,(2,1), input_shape=(2, 2, 1), activation='relu'))
model.add(Flatten())
model.add(Dense(32, activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dense(16, activation='relu'))
model.add(Dense(1))
# model.summary()

# 컴파일 훈련
model.compile(loss='mse', optimizer='adam')
model.fit(x_train, y_train, epochs=100, batch_size=8, verbose=3)

# 평가, 예측
loss = model.evaluate(x_test, y_test)
print('loss :', loss)
result =model.predict(x_predict)
print('예측 결과 :', result)

# 예측 결과 : [[100.00106 ]
#  [101.01175 ]
#  [102.029945]
#  [103.04814 ]
#  [104.06633 ]
#  [105.084526]
#  [106.10274 ]