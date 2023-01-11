import numpy as np
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense

x = np.array([[1,2,3,4,5,6,7,8,9,10],
              [1,1,1,1,2,1.3,1.4,1.5,1.6,1.4],
              [1,2,3,4,5,6,7,8,9,10],
              [5,6,7,8,9,10,11,12,13,14],
              [5,6,7,1,2,3,4,8,9,4]])

y = np.array([5,10,15,20,25,30,35,40,45,50])

print(x.shape)
print(y.shape)

x = x.T
print(x.shape)

model = Sequential()
model.add(Dense(10,input_dim=5))
model.add(Dense(15))
model.add(Dense(20))
model.add(Dense(10))
model.add(Dense(1))

model.compile(loss='mae', optimizer='adam')
model.fit(x, y, epochs=2500, batch_size=32)

loss = model.evaluate(x, y)
print('loss :', loss)
result = model.predict([[10,1.4,10,14,4]])
print('[10, 1.4]의 예측값 :',result)









