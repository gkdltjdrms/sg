from sklearn.datasets import load_diabetes
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from sklearn.model_selection import train_test_split

#1. 데이터
datasets = load_diabetes()
x = datasets.data
y = datasets.target
# print(x.shape, y.shape)  # (506, 13)   (506,)

x_train, x_test, y_train, y_test = train_test_split(x, y,
        shuffle=True, random_state=333, test_size=0.2)

#2. 모델구성 
model = Sequential()
# model.add(Dense(5, input_dim=13))     # input_dim = 2차원에서만 사용 가능하다
model.add(Dense(6, input_shape=(10,)))   # input_shape = 다차원에서 사용가능
model.add(Dense(32))
model.add(Dense(100))
model.add(Dense(300))
model.add(Dense(1))

#3 컴파일, 훈련
model.compile(loss='mse', optimizer='adam')
#대문자는 파이썬의 클래스로 등록
from tensorflow.keras.callbacks import EarlyStopping 
# mode= auto, min, max 
earlystopping = EarlyStopping(monitor='val_loss',
                              mode='min', 
                              patience=100,
                              restore_best_weights=True,
                              verbose=3)
 
hist = model.fit(x_train, y_train, epochs=1000, batch_size=32,
          validation_split=0.2, callbacks=[earlystopping],
          verbose=3)

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
plt.title('boston loss')
plt.legend()   # 선의 라벨이 나오게 된다
# plt.legend(loc= 'upper left')
plt.show()





























