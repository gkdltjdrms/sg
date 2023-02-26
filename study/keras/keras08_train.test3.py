import numpy as np
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from sklearn.model_selection import train_test_split 


#1 . 데이터
# x = np. array([1,2,3,4,5,6,7,8,9,10])
# y = np. array(range(10))
x = np.array([1,2,3,4,5,6,7,8,9,10])
y = np.array(range(10))

#실습 : 넘파이 리스트 슬라이싱 7:3으로 잘라라
# x_train, x_test = train_test_split(x, test_size=0.3)
# y_train, y_test = train_test_split(y, test_size=0.3)


x_train, x_test, y_train, y_test = train_test_split(
    x, y,
    train_size=0.7,
    # test_size=0.3,
    # shuffle=True,
    random_state=123
    )


# x_train = x[:-3]
# x_test = x[7:]

# y_train = y[:7]
# y_test = y[-2:]


# [검색] train과 test를 섞어서 7:3으로 만들기
# 힌트 : 사이킷런
# x_train, x_test, y_train, y_test = train_test_split(
#   iris.data, iris.target, test_size=0.3,random_state=1234 )


print('x_train :', x_train)
print('y_train :', y_train)
print('x_test :', x_test)
print('y_test :', y_test)



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
 














\
