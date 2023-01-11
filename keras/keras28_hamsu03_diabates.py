from sklearn.datasets import load_diabetes
from tensorflow.keras.models import Sequential,Model
from tensorflow.keras.layers import Dense,Input
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import MinMaxScaler, StandardScaler
import numpy as np
#1. 데이터
datasets = load_diabetes()
x = datasets.data
y = datasets.target
# print(x.shape, y.shape)  # (506, 13)   (506,)

x_train, x_test, y_train, y_test = train_test_split(x, y,
        shuffle=True, random_state=333, test_size=0.2)

scaler = MinMaxScaler()  # train을 기준으로 삼는다
scaler.fit(x_train)
x_train = scaler.transform(x_train)
# x_train = scaler.fit_transform(X_train)
x_test = scaler.transform(x_test)

# #2. 모델구성 
# model = Sequential()
# # model.add(Dense(5, input_dim=13))     # input_dim = 2차원에서만 사용 가능하다
# model.add(Dense(6, input_shape=(10,)))   # input_shape = 다차원에서 사용가능
# model.add(Dense(32))
# model.add(Dense(100))
# model.add(Dense(300))
# model.add(Dense(1))


#2. 모델 구성 (함수형)

input1 = Input(shape=(10,))
dense1 = Dense(50, activation='relu')(input1)
dense2 = Dense(40, activation='sigmoid')(dense1)
dense3 = Dense(50, activation='relu')(dense2)
dense4 = Dense(50, activation='linear')(dense3)
output1 = Dense(1, activation='linear')(dense4)
model = Model(inputs=input1, outputs = output1)
model.summary()  #4,611



#3 컴파일, 훈련
model.compile(loss='mse', optimizer='adam')
#대문자는 파이썬의 클래스로 등록
from tensorflow.keras.callbacks import EarlyStopping 
# mode= auto, min, max 
earlystopping = EarlyStopping(monitor='val_loss',
                              mode='min', 
                              patience=100,
                              restore_best_weights=True,
                              verbose=1)
 
hist = model.fit(x_train, y_train, epochs=1000, batch_size=32,
          validation_split=0.2, callbacks=[earlystopping],
          verbose=1)

#4. 평가, 예측
loss = model.evaluate(x_test, y_test)
print('loss :', loss)

# print('=================================')
# print(hist) # <keras.callbacks.History object at 0x000001C641639A90>
# print('=================================')
# print(hist.history) 
# print('=================================')
# print(hist.history['loss'])  
# print('=================================')
# print(hist.history['val_loss'])  


import matplotlib.pyplot as plt

plt.figure(figsize=(9,6))
plt.plot(hist.history['loss'], c = 'red',
         marker= '.', label='loss')
plt.plot(hist.history['val_loss'], c = 'blue',
         marker= '.', label= 'val_loss')
plt.grid()  # 격자
plt.xlabel('epochs')
plt.ylabel('loss')
plt.title('diabates loss')
plt.legend()   # 선의 라벨이 나오게 된다
# plt.legend(loc= 'upper left')
plt.show()

# y_predict = model.predict(x_test)
# from sklearn.metrics import mean_squared_error, r2_score
# def RMSE(y_test, y_predict):
#     return np.sqrt(mean_squared_error(y_test, y_predict))

# print('RMSE :', RMSE(y_test, y_predict))

# r2 =  r2_score(y_test, y_predict)
# print('R2 :', r2)
# ## scaler acc : 



























