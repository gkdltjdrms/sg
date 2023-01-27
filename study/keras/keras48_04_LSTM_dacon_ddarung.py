from tensorflow.keras.models import Sequential,Model
from tensorflow.keras.layers import Dense,Input,LSTM, Dropout, Conv2D, Flatten
from sklearn.model_selection import train_test_split
import numpy as np
import pandas as pd
from sklearn.metrics import mean_squared_error, r2_score
from sklearn.preprocessing import MinMaxScaler, StandardScaler
#1. 데이터
path ='./_data/ddarung/'

train_csv = pd.read_csv(path+'train.csv', index_col=0)
test_csv = pd.read_csv(path+'test.csv', index_col=0)
# print(train_csv)
submission = pd.read_csv(path + 'submission.csv', index_col=0)

train_csv = train_csv.dropna()

x = train_csv.drop(['count'], axis=1)
y = train_csv['count']

x_train, x_test, y_train, y_test = train_test_split(x, y,
      train_size=0.7,
      shuffle=True,
      random_state=123           )

scaler = MinMaxScaler()  # train을 기준으로 삼는다
scaler.fit(x_train)
x_train = scaler.transform(x_train)
# x_train = scaler.fit_transform(X_train)
x_test = scaler.transform(x_test)
test_csv = scaler.transform(test_csv)

print(x_train.shape, x_test.shape)  # (929, 9) (399, 9)
x_train = x_train.reshape(929, 9, 1, 1)
x_test = x_test.reshape(399, 9, 1, 1)
test_csv = test_csv.reshape(715,9,1,1)
# print(test_csv.shape) #(715, 9)
# print(x_train.shape, x_test.shape)  # 
# #2. 모델구성 
model = Sequential()
model.add(LSTM(64, input_shape=(9,1)))
model.add(Flatten())
model.add(Dense(64, activation='relu'))
model.add(Dropout(0.2))
model.add(Dense(32, activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dense(1))
model.summary()  




#3 컴파일, 훈련
model.compile(loss='mse', optimizer='adam')
#대문자는 파이썬의 클래스로 등록
from tensorflow.keras.callbacks import EarlyStopping 
# mode= auto, min, max 
earlystopping = EarlyStopping(monitor='val_loss',
                              mode='min', 
                              patience=200,
                              restore_best_weights=True,
                              verbose=1)
 
hist = model.fit(x_train, y_train, epochs=20, batch_size=8,
          validation_split=0.2, callbacks=[earlystopping],
          verbose=3)

#4. 평가, 예측
loss = model.evaluate(x_test, y_test)
print('loss :', loss)

y_predict = model.predict(x_test)
def RMSE(y_test, y_predict):
    return np.sqrt(mean_squared_error(y_test, y_predict))



import matplotlib.pyplot as plt
y_submit = model.predict(test_csv)
submission['count'] = y_submit
submission.to_csv(path+ 'submission_0111459.csv')

print('RMSE :', RMSE(y_test, y_predict))

# plt.figure(figsize=(9,6))
# plt.plot(hist.history['loss'], c = 'red',
#          marker= '.', label='loss')
# plt.plot(hist.history['val_loss'], c = 'blue',
#          marker= '.', label= 'val_loss')
# plt.grid()  # 격자
# plt.xlabel('epochs')
# plt.ylabel('loss')
# plt.title('ddarung loss')
# plt.legend()   # 선의 라벨이 나오게 된다
# # plt.legend(loc= 'upper left')
# plt.show()


# 135    41

#rmse = 44.296
#rmse =41.6901  

#1 104






















