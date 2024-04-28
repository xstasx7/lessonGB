import logging


# Модель
class CalculatorModel:
    def add(self, x, y):
        return x + y

    def multiply(self, x, y):
        return x * y

    def divide(self, x, y):
        if y == 0:
            raise ValueError("На ноль делить нельзя")
        return x / y

    def __repr__(self):
        return f"{self.x}{self.y}"


# Представление
class CalculatorView:
    def get_user_input(self):
        return input("Введите выражение (например, 5 + 5): ")

    def show_result(self, result):
        print("Результат:", result)


# Контроллер
class CalculatorController:
    def __init__(self, model, view):
        self.model = model
        self.view = view

    def run(self):
        while True:
            user_input = self.view.get_user_input()
            if user_input.lower() == 'выход':
                break

            try:
                parts = user_input.split()
                if len(parts) != 3:
                    raise ValueError("Неверный формат выражения")

                num1 = float(parts[0])
                operator = parts[1]
                num2 = float(parts[2])

                if operator == '+':
                    result = self.model.add(num1, num2)
                elif operator == '*':
                    result = self.model.multiply(num1, num2)
                elif operator == '/':
                    result = self.model.divide(num1, num2)
                else:
                    raise ValueError("Неподдерживаемая операция")

                self.view.show_result(result)
            except Exception as e:
                print("Ошибка:", e)


# Логирование
logging.basicConfig(filename='calculator.log', level=logging.INFO)


def log_operation(operation):
    def decorator(func):
        def wrapper(*args, **kwargs):
            result = func(*args, **kwargs)
            logging.info(f"{operation} {args} = {result}")
            return result

        return wrapper

    return decorator


class LoggedCalculatorModel(CalculatorModel):
    @log_operation("Addition")
    def add(self, x, y):
        return super().add(x, y)

    @log_operation("Multiplication")
    def multiply(self, x, y):
        return super().multiply(x, y)

    @log_operation("Division")
    def divide(self, x, y):
        return super().divide(x, y)


if __name__ == "__main__":
    model = LoggedCalculatorModel()
    view = CalculatorView()
    controller = CalculatorController(model, view)
    controller.run()
