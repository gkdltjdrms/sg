from sklearn.datasets import load_boston
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense
from sklearn.model_selection import train_test_split

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
model.add(Dense(4))
model.add(Dense(3))
model.add(Dense(2))
model.add(Dense(1))

#3 컴파일, 훈련
import time
model.compile(loss='mse', optimizer='adam')
start = time.time()
model.fit(x_train, y_train, epochs=50, batch_size=1,
          validation_split=0.2,
          verbose=4)
end = time.time()
#3. 평가, 예측
loss = model.evaluate(x_test, y_test)
print('loss :', loss)

print('걸린시간 :', end - start)
# verbose 1  걸린시간 : 13.2452
# verbose 0 걸린시간 : 10.7633
# 2 : 프로그래스바 제거
# 4 : 에포만 나온다





