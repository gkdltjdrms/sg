import numpy as np
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense

# 1. 데이터
x = np.array([range(10)])
# print (range(10))
# print(x.shape)    # (3, 10)
y = np.array([[1,2,3,4,5,6,7,8,9,10],
             [1,1,1,1,2,1.3,1.4,1.5,1.6,1.4],
             [9,8,7,6,5,4,3,2,1,0]])

x =  x.T
y =  y.T
# 행무시 열우선

#2. 모델 구성
model = Sequential()
model.add(Dense(10, input_dim=1))
model.add(Dense(30))
model.add(Dense(5))
model.add(Dense(10))
model.add(Dense(30))
model.add(Dense(30))
model.add(Dense(30))
model.add(Dense(15))
model.add(Dense(3))

#3. 컴파일, 훈련  #행무시 열우선
model.compile(loss='mae', optimizer='adam')
model.fit(x, y, epochs=5000, batch_size=32)

#  평가, 예측
loss = model.evaluate(x, y)
print('loss :', loss)

result = model.predict([[9]])
print('예측값 :', result)





