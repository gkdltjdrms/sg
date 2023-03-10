from tensorflow.keras.models import Sequential,Model
from tensorflow.keras.layers import Dense,Input,LSTM,Conv1D, Dropout, Conv2D, Flatten
from sklearn.model_selection import train_test_split
import numpy as np
import pandas as pd
from sklearn.metrics import mean_squared_error, r2_score
from sklearn.preprocessing import MinMaxScaler, StandardScaler
#1. 데이터
path ='./_data/bike/'
train_csv = pd.read_csv(path+'train.csv', index_col=0)
test_csv = pd.read_csv(path+'test.csv', index_col=0)
submission = pd.read_csv(path + 'samplesubmission.csv', index_col=0)

# train_csv = train_csv.dropna()

train_csv = train_csv.drop(['casual', 'registered'], axis=1)

x = train_csv.drop(['count'], axis=1)
y = train_csv['count']

x_train, x_test, y_train, y_test = train_test_split(x, y,
      train_size=0.7,
      shuffle=True,
      random_state=111           )
scaler = MinMaxScaler()  # train을 기준으로 삼는다
scaler.fit(x_train)
x_train = scaler.transform(x_train)
x_test = scaler.transform(x_test)
test_csv = scaler.transform(test_csv)

print(x_train.shape, x_test.shape)  # (7620, 8) (3266, 8)
x_train = x_train.reshape(7620, 8, 1, 1)
x_test = x_test.reshape(3266, 8, 1, 1)
test_csv = test_csv.reshape(6493,8,1,1)
# # #2. 모델구성 
model = Sequential()
model.add(Conv1D(64,2, input_shape=(8,1),
                 activation='relu'))
model.add(Flatten())
model.add(Dense(64, activation='relu'))
model.add(Dropout(0.25))
model.add(Dense(32, activation='relu'))
model.add(Dropout(0.25))
model.add(Dense(32, activation='relu'))
model.add(Dense(32, activation='relu'))
model.add(Dense(1))
model.summary()  

# #2. 모델 구성 (함수형)

# # input1 = Input(shape=(8,))
# # dense1 = Dense(50, activation='relu')(input1)
# # drop1 = Dropout(0.5)(dense1)
# # dense2 = Dense(40, activation='sigmoid')(drop1)
# # drop2 = Dropout(0.5)(dense2)
# # dense3 = Dense(50, activation='relu')(drop2)
# # dense4 = Dense(50, activation='linear')(dense3)
# # output1 = Dense(1, activation='linear')(dense4)

# # model = Model(inputs=input1, outputs = output1)
# # model.summary()  #4,611



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
 
hist = model.fit(x_train, y_train, epochs=5000, batch_size=128,
          validation_split=0.2, callbacks=[earlystopping],
          verbose=1)

#4. 평가, 예측
loss = model.evaluate(x_test, y_test)
print('loss :', loss)

y_predict = model.predict(x_test)
def RMSE(y_test, y_predict):
    return np.sqrt(mean_squared_error(y_test, y_predict))
print('RMSE :', RMSE(y_test, y_predict))
r2 = r2_score(y_test, y_predict)
print("R2: ", r2)

y_submit = model.predict(test_csv)
submission['count'] = y_submit
# submission.to_csv(path+ 'submission_012543.csv')

# import matplotlib.pyplot as plt

# plt.figure(figsize=(9,6))
# plt.plot(hist.history['loss'], c = 'red',
#          marker= '.', label='loss')
# plt.plot(hist.history['val_loss'], c = 'blue',
#          marker= '.', label= 'val_loss')
# plt.grid()  # 격자
# plt.xlabel('epochs')
# plt.ylabel('loss')
# plt.title('kaggle bike loss')
# plt.legend()   # 선의 라벨이 나오게 된다
# # plt.legend(loc= 'upper left')
# plt.show()

# #    148.13
# ## scaler 사용후 : 145.45


























