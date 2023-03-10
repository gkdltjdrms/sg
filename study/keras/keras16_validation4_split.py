import numpy as np
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from sklearn.model_selection import train_test_split

#1. 데이터

x = np.array(range(1, 17))
y = np.array(range(1, 17))

x_validation = x[13:17]
y_validation = y[13:17]

x_train, x_test, y_train, y_test = train_test_split(x, y,
      train_size=0.2,
      shuffle=False,
      random_state=1234           )

print(x_train.shape, x_test.shape) #


model = Sequential()
model.add(Dense(10, input_dim=1))
model.add(Dense(15, activation='relu'))
model.add(Dense(25, activation='relu'))
model.add(Dense(20, activation='relu'))
model.add(Dense(15, activation='relu'))
model.add(Dense(1))


model.compile(loss='mse', optimizer='adam')
model.fit(x_train, y_train, epochs=250, batch_size=1,
          validation_split=0.25)
# 기준은 val loss로 잡는다 
loss = model.evaluate(x_test, y_test)
print('loss :', loss)

result = model.predict([17])
print('17의 예측값 :', result)










