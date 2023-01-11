# https://bskyvision.com/entry/python-matplotlibpyplot%EB%A1%9C-%EA%B7%B8%EB%9E%98%ED%94%84-%EA%B7%B8%EB%A6%B4-%EB%95%8C-%ED%95%9C%EA%B8%80-%EA%B9%A8%EC%A7%90-%EB%AC%B8%EC%A0%9C-%ED%95%B4%EA%B2%B0-%EB%B0%A9%EB%B2%95
# 한글깨짐 수정


## matplotlib 한글깨짐 수정하기 
from sklearn.datasets import load_boston
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from sklearn.model_selection import train_test_split

import matplotlib.pyplot as plt
from matplotlib import font_manager, rc
font_path = "C:\Windows\Fonts\malgun.ttf"
font = font_manager.FontProperties(fname=font_path).get_name()
rc('font', family=font)








#1. 데이터
datasets = load_boston()
x = datasets.data
y = datasets.target
# print(x.shape, y.shape)  # (506, 13)   (506,)

x_train, x_test, y_train, y_test = train_test_split(x, y,
        shuffle=True, random_state=333, test_size=0.2)

#2. 모델구성 
model = Sequential()
# model.add(Dense(5, input_dim=13))     # input_dim = 2차원에서만 사용 가능하다
model.add(Dense(5, input_shape=(13,)))   # input_shape = 다차원에서 사용가능
model.add(Dense(40000))
model.add(Dense(3))
model.add(Dense(20000))
model.add(Dense(1))

#3 컴파일, 훈련
model.compile(loss='mse', optimizer='adam')
hist = model.fit(x_train, y_train, epochs=5, batch_size=1,
          validation_split=0.2,
          verbose=3)

#3. 평가, 예측
loss = model.evaluate(x_test, y_test)
print('loss :', loss)

print('=================================')
print(hist) # <keras.callbacks.History object at 0x000001C641639A90>
print('=================================')
print(hist.history) 
print('=================================')
print(hist.history['loss'])  
print('=================================')
print(hist.history['val_loss'])  



plt.figure(figsize=(9,6))
plt.plot(hist.history['loss'], c = 'red',
         marker= '.', label='loss')
plt.plot(hist.history['val_loss'], c = 'blue',
         marker= '.', label= 'val_loss')



plt.grid()  # 격자
plt.xlabel('epochs')
plt.ylabel('loss')
plt.title('보스톤 loss')
plt.legend()   # 선의 라벨이 나오게 된다
# plt.legend(loc= 'upper left')
plt.show()











