import numpy as np
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense


#1 . 데이터
# x = np. array([1,2,3,4,5,6,7,8,9,10])
# y = np. array(range(10))
x = np.array([1,2,3,4,5,6,7,8,9,10])
y = np.array(range(10))

#실습 : 넘파이 리스트 슬라이싱 7:3으로 잘라라
x_train = x[:-3]
x_test = x[7:]

y_train = y[:7]
y_test = y[-2:]

print(x_train)
print(y_train)
print(x_test)
print(y_test)


# #2. 모델 구성
# model = Sequential()
# model.add(Dense(30, input_dim=1))
# model.add(Dense(40))
# model.add(Dense(50))
# model.add(Dense(50))
# model.add(Dense(40))
# model.add(Dense(30))
# model.add(Dense(1))

# #3. 컴파일, 훈련
# model.compile(loss='mae', optimizer='adam')
# model.fit(x_train, y_train, epochs=100, batch_size=32)

# #4. 평가, 예측
# loss= model.evaluate(x_test, y_test)
# print('loss :',  loss)
# result = model.predict([11])
# print('[11의 예측값] :', result)
 














