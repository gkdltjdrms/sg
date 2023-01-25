from tensorflow.keras.models import Sequential,Model
from tensorflow.keras.layers import Dense,Input,Dropout
from sklearn.model_selection import train_test_split
import numpy as np
import pandas as pd
from sklearn.metrics import mean_squared_error, r2_score
from sklearn.preprocessing import MinMaxScaler, StandardScaler
#1. 데이터
path ='./_data/bike/'
train_csv = pd.read_csv(path+'train.csv', index_col=0)
test_csv = pd.read_csv(path+'test.csv', index_col=0)
print(train_csv)
submission = pd.read_csv(path + 'samplesubmission.csv',
                         index_col=0)

# train_csv = train_csv.dropna()

x = train_csv.drop(['casual', 'registered', 'count'], axis=1)
y = train_csv['count']

x_train, x_test, y_train, y_test = train_test_split(x, y,
      train_size=0.7,
      shuffle=True,
      random_state=111 )


scaler = MinMaxScaler()  # train을 기준으로 삼는다
scaler.fit(x_train)
x_train = scaler.transform(x_train)
# x_train = scaler.fit_transform(X_train)
x_test = scaler.transform(x_test)
test_csv = scaler.transform(test_csv)
# #2. 모델구성 
model = Sequential()
# model.add(Dense(5, input_dim=13))     # input_dim = 2차원에서만 사용 가능하다
model.add(Dense(50, input_shape=(8,),activation='relu'))   # input_shape = 다차원에서 사용가능
model.add(Dense(40, activation='relu'))
model.add(Dense(40, activation='relu'))
model.add(Dense(40, activation='relu'))
model.add(Dense(40, activation='relu'))
model.add(Dense(50, activation='relu'))
model.add(Dense(1, activation='relu'))

#2. 모델 구성 (함수형)

# input1 = Input(shape=(8,))
# dense1 = Dense(50, activation='relu')(input1)
# drop1 = Dropout(0.5)(dense1)
# dense2 = Dense(40, activation='sigmoid')(drop1)
# drop2 = Dropout(0.5)(dense2)
# dense3 = Dense(50, activation='relu')(drop2)
# dense4 = Dense(50, activation='linear')(dense3)
# output1 = Dense(1, activation='linear')(dense4)

# model = Model(inputs=input1, outputs = output1)
# model.summary()  #4,611



#3 컴파일, 훈련
model.compile(loss='mse', optimizer='adam')
#대문자는 파이썬의 클래스로 등록
from tensorflow.keras.callbacks import EarlyStopping 
# mode= auto, min, max 
earlystopping = EarlyStopping(monitor='val_loss',
                              mode='min', 
                              patience=300,
                              restore_best_weights=True,
                              verbose=1)
 
hist = model.fit(x_train, y_train, epochs=3, batch_size=32,
          validation_split=0.2, callbacks=[earlystopping],
          verbose=3)

#4. 평가, 예측
loss = model.evaluate(x_test, y_test)
print('loss :', loss)

y_predict = model.predict(x_test)
def RMSE(y_test, y_predict):
    return np.sqrt(mean_squared_error(y_test, y_predict))
# print('=================================')
# print(hist) # <keras.callbacks.History object at 0x000001C641639A90>
# print('=================================')
# print(hist.history) 
# print('=================================')
# print(hist.history['loss'])  
# print('=================================')
# print(hist.history['val_loss'])  
print('RMSE :', RMSE(y_test, y_predict))


y_submit = model.predict(test_csv)
submission['count'] = y_submit
submission.to_csv(path+ 'submission_0111450.csv')

import matplotlib.pyplot as plt

plt.figure(figsize=(9,6))
plt.plot(hist.history['loss'], c = 'red',
         marker= '.', label='loss')
plt.plot(hist.history['val_loss'], c = 'blue',
         marker= '.', label= 'val_loss')
plt.grid()  # 격자
plt.xlabel('epochs')
plt.ylabel('loss')
plt.title('kaggle bike loss')
plt.legend()   # 선의 라벨이 나오게 된다
# plt.legend(loc= 'upper left')
plt.show()

#    148.13
## scaler 사용후 : 145.45


























