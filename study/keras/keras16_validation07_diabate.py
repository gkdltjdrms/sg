# [과제, 실습]
# [R2: 0.62이상]
from sklearn.datasets import load_diabetes
import sklearn as sk
# print(sk.__version__)  #1.1.3

from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
import numpy as np
from sklearn.model_selection import train_test_split


#1. 데이터
datasets = load_diabetes()
  
x = datasets.data
y = datasets.target

print (x.shape)
x_train, x_test, y_train, y_test = train_test_split(x, y,
      train_size=0.7,
      shuffle=True,
      random_state=111                                                    
                                                    )
model = Sequential()
model.add(Dense(20, input_dim=10, activation='relu'))
model.add(Dense(30, activation='relu'))
model.add(Dense(30, activation='relu'))
model.add(Dense(30, activation='relu'))
model.add(Dense(1))

model.compile(loss='mse', optimizer='adam',
              metrics=['mse'])
model.fit(x_train, y_train, epochs= 1000, batch_size=32,
          validation_split=0.25)

loss = model.evaluate(x_test, y_test)
print('loss :',  loss)

y_predict = model.predict(x_test)

from sklearn.metrics import mean_squared_error, r2_score
def RMSE(y_test, y_predict):
    return np.sqrt(mean_squared_error(y_test, y_predict))

print('RMSE :', RMSE(y_test, y_predict))

r2 =  r2_score(y_test, y_predict)
print('R2 :', r2)