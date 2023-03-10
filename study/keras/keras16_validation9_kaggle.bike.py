import numpy as np
import pandas as pd
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error, r2_score
import time

#1. 데이터
path ='./_data/bike/'
train_csv = pd.read_csv(path+'train.csv', index_col=0)
test_csv = pd.read_csv(path+'test.csv', index_col=0)
print(train_csv)
submission = pd.read_csv(path + 'samplesubmission.csv',
                         index_col=0)


print(train_csv.shape)       #(10886 , 11)
print(train_csv.columns)     #(10886, 11)
print(test_csv.info())
print(train_csv.info())
print(train_csv.describe())

print(train_csv.isnull().sum())
train_csv=train_csv.dropna()
print(train_csv.isnull().sum())
print(train_csv.shape)    #(1086, 11)


x = train_csv.drop(['casual', 'registered', 'count'], axis=1)
print(x)   
y = train_csv['count']    # 10886 rows x 10 columns
print(y)
print(y.shape)   # (10886,)

x_train, x_test, y_train, y_test = train_test_split(x, y,
      train_size=0.7,
      shuffle=True,
      random_state=111           )

print(x_train.shape, x_test.shape) 
print(y_train.shape, y_test.shape)  


# 모델 구성
model = Sequential()
model.add(Dense(30, input_dim=8, activation='relu'))
model.add(Dense(50, activation='relu'))
model.add(Dense(60, activation='relu'))
model.add(Dense(60, activation='relu'))
model.add(Dense(60, activation='relu'))
model.add(Dense(40, activation='relu'))
model.add(Dense(1,))


model.compile(loss='mse', optimizer='adam')
start = time.time()
model.fit(x_train, y_train, epochs= 130, batch_size=32,
          validation_split=0.25)
end = time.time()

#4 평가, 예측

loss = model.evaluate(x_test, y_test)
print('loss :',  loss)

y_predict = model.predict(x_test)

def RMSE(y_test, y_predict):
    return np.sqrt(mean_squared_error(y_test, y_predict))


print('RMSE :', RMSE(y_test, y_predict))

print('걸린시간 :', end - start)

y_submit = model.predict(test_csv)
submission['count'] = y_submit
submission.to_csv(path+ 'submission_01060131.csv')



# rmse :   여기는 validation을 사용했을때