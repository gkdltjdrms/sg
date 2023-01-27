from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, Conv2D, Flatten

model = Sequential()
# 데이터의 갯수는 무시한다
model.add(Conv2D(filters=5, kernel_size=(2,2), # 이미지를 조각내는 사이즈
                 input_shape=(10, 10, 1)))  # (N, 4, 4, 10)
                 # (batch_size, rows, columns, channels )
model.add(Conv2D(5, (2,2))) # (N, 3, 3, 5)
model.add(Conv2D(7, (2))) 
model.add(Flatten()) # (N, 45)
model.add(Dense(units=10)) # (N, 10)
       # 인풋은 (batch_size, input_dim)
model.add(Dense(4, activation='relu'))  #      (N, 1)
# N = 데이터의 개수
model.summary() # 726
 



