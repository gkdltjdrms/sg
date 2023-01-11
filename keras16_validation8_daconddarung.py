
import numpy as np
import pandas as pd
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error, r2_score

#1. 데이터 
path ='./_data/ddarung/'
train_csv = pd.read_csv(path+'train.csv', index_col=0)
test_csv = pd.read_csv(path+'test.csv', index_col=0)
print(train_csv)
submission = pd.read_csv(path + 'submission.csv', index_col=0)

print(train_csv.shape)
print(train_csv.columns)
print(test_csv.info())
print(train_csv.info())
print(train_csv.describe())

print(train_csv.isnull().sum())
train_csv=train_csv.dropna()
print(train_csv.isnull().sum())
print(train_csv.shape)    #(1320, 10)



x = train_csv.drop(['count'], axis=1)
print(x)   #[1459 rows x 9 colums]
y = train_csv['count']
print(y)
print(y.shape)# (1459, )
# 결측치 있는 데이터를 삭제
# 결측치 처리 1. 제거  ######




x_train, x_test, y_train, y_test = train_test_split(x, y,
      train_size=0.7,
      shuffle=True,
      random_state=121           )

print(x_train.shape, x_test.shape) # (1021, 9), (438, 9)
print(y_train.shape, y_test.shape) # (1021,), (438,)

#2 모델구성

model = Sequential()
model.add(Dense(60, input_dim=9,activation='relu'))
model.add(Dense(60, activation='relu'))
model.add(Dense(60, activation='relu'))
model.add(Dense(60, activation='relu'))
model.add(Dense(60, activation='relu'))
model.add(Dense(1))

#3 컴파일, 훈련
import time

model.compile(loss='mse', optimizer='adam',)
start= time.time()
model.fit(x_train, y_train, epochs= 10, batch_size=32,
          validation_split=0.25)
end = time.time()
#4 평가, 예측
loss = model.evaluate(x_test, y_test)
print('loss :',  loss)

y_predict = model.predict(x_test)
# print(y_predict)
#결측치가 안좋다
#결측치 때문에 이후에 
def RMSE(y_test, y_predict):
    return np.sqrt(mean_squared_error(y_test, y_predict))


print('RMSE :', RMSE(y_test, y_predict))

print('걸린시간 :', end - start)
#제출할것


y_submit = model.predict(test_csv)
# print(y_submit)
# print(y_submit.shape)  # (715, 1)


# print(submission)
submission['count'] = y_submit
# print(submission)

submission.to_csv(path+ 'submission_01050310.csv')

#cpu 걸린시간 3.5598
#gpu 걸린시간9.5642


# r2 =  r2_score(y_test, y_predict)
# print('R2 :', r2)



#C:\Program Files\NVIDIA GPU Computing Toolkit\CUDA\v11.4 cuda위치






