class Car(object):
    def __init__(self, model, passengers, color, speed):
        self.model = model
        self.passengers = passengers
        self.color = color
        self.speed = speed

    def accelerate(self):
        self.speed = self.speed + 3
        print (self.speed)

volvo = Car("Volvo", 5, "white", 60)
ferrari = Car("Ferrari", 2, "black", 100)
tesla = Car("Ford", 4, "green", 90)

volvo.accelerate()
print (bmw.color)

ferrari.accelerate()
print (ferrari.color)
ferrari.accelerate() 

print (ford.passengers)
ford.accelerate()
