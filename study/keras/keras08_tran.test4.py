import numpy as np
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from sklearn.model_selection import train_test_split

# 1. 데이터
x = np.array([range(10),range(21, 31),range(201, 211)])
# print (range(10))
# print(x.shape)    # (3, 10)
y = np.array([[1,2,3,4,5,6,7,8,9,10],
             [1,1,1,1,2,1.3,1.4,1.5,1.6,1.4]])
x =  x.T
y =  y.T

# [실습] train_test_split를 이용하여
# 7:3으로 잘라서 모델 구현 / 소스 완성
x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    test_size=0.3,
    shuffle=False,
    random_state=123
)


print('x_train :', x_train)
print('y_train :', y_train)
print('x_test :', x_test)
print('y_test :', y_test)






#2. 모델 구성
model = Sequential()
model.add(Dense(10, input_dim=3))
model.add(Dense(30))
model.add(Dense(5))
model.add(Dense(10))
model.add(Dense(30))
model.add(Dense(30))
model.add(Dense(30))
model.add(Dense(15))
model.add(Dense(2))

#3. 컴파일, 훈련  #행무시 열우선
model.compile(loss='mae', optimizer='adam')
model.fit(x, y, epochs=200, batch_size=32)

#  평가, 예측
loss = model.evaluate(x, y)
print('loss :', loss)

result = model.predict([[9,30,210]])
print('예측값 :', result)
###
