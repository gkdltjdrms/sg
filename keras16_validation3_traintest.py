import numpy as np
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from sklearn.model_selection import train_test_split

#1. 데이터

x = np.array(range(1, 17))
y = np.array(range(1, 17))

x_validation = x[13:17]
y_validation = y[13:17]


# 실습 자르기
# x_train = x[:10]
# x_test = x[11:14]
# y_train = y[:10]
# y_test = y[11:14]
# x_validation = x[14:17]
# y_validation = y[14:17]

x_train, x_tmp, y_train, y_tmp = train_test_split(x, y,
      train_size=0.625,
      shuffle=False,
      random_state=123           )

# print("x_train",x_train)
# print("y_tmp",x_tmp)
# print(y_train)
# print(y_test)



x_test, x_validation, y_test, y_validation = train_test_split(x_tmp,
                                                    y_tmp,
      train_size=0.5,
      shuffle=False,
      random_state=123           )



print (x_train, y_train)
print (x_test, y_test)
print (x_validation,y_validation)



model = Sequential()
model.add(Dense(10, input_dim=1))
model.add(Dense(15, activation='relu'))
model.add(Dense(25, activation='relu'))
model.add(Dense(20, activation='relu'))
model.add(Dense(15, activation='relu'))
model.add(Dense(1))


model.compile(loss='mse', optimizer='adam')
model.fit(x_train, y_train, epochs=250, batch_size=1,
        validation_data=(x_validation, y_validation))

loss = model.evaluate(x_test, y_test)
print('loss :', loss)

result = model.predict([17])
print('17의 예측값 :', result)

# print(x_train)
# print(x_test)
# print(y_train)
# print(y_test)
# print(x_train)
# print(x_test)
# print(y_train)
# print(y_test)

# print(x)
# print(y)



# x_train = np.array(range(1,11))
# y_train = np.array(range(1,11))

# x_test = np.array([11,12,13])
# y_test = np.array([11,12,13])

# x_validation = np.array([14,15,16])
# y_validation = np.array([14,15,16])

# #2. 모델구성 
# model = Sequential()
# model.add(Dense(10, input_dim=1))
# model.add(Dense(15, activation='relu'))
# model.add(Dense(25, activation='relu'))
# model.add(Dense(20, activation='relu'))
# model.add(Dense(15, activation='relu'))
# model.add(Dense(1))

# #3. 컴파일, 훈련
# model.compile(loss='mse', optimizer='adam')
# model.fit(x_train, y_train, epochs=250, batch_size=1,
#         validation_data=(x_validation, y_validation))


# #4. 평가, 예측
# loss = model.evaluate(x_test, y_test)
# print('loss :', loss)

# result = model.predict([17])
# print('17의 예측값 :', result)


