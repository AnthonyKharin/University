import unittest
import task3


class TestTask3(unittest.TestCase):

    def test_solution(self):
        # test 1
        self.assertEqual(task3.solution("..\\Sudoku1"), "YES")
        # test 2
        self.assertEqual(task3.solution("..\\Sudoku2"), "NO")