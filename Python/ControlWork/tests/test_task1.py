import task1
import math
import unittest


class TestTask1(unittest.TestCase):

    def test_solution(self):
        # You can't use assertEqual because we have to check the neighborhood of the number

        # test 1
        x = 2
        eps = 1
        answer = 7
        if math.fabs(task1.solution(x, eps) - answer) > eps:
            self.fail()

        # test 2
        x = 1.0
        eps = 0.001
        answer = 2.718
        if math.fabs(task1.solution(x, eps) - answer) > eps:
            self.fail()
