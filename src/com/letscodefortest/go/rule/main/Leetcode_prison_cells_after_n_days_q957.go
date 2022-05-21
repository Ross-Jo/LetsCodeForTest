package main

import (
	"fmt"
)

/*
	Runtime: 3 ms, faster than 48.28% of Go online submissions for Prison Cells After N Days.
	Memory Usage: 2.2 MB, less than 82.76% of Go online submissions for Prison Cells After N Day
*/
func prisonAfterNDays(cells []int, n int) []int {
	if n%14 == 0 {
		n = 14
	} else {
		n = n % 14
	}

	for i := 0; i < n; i++ {
		cells = flip(cells)
	}

	return cells
}

func flip(cells []int) []int {
	ret := make([]int, 8)

	for i := 0; i < 8; i++ {
		if i == 0 || i == 7 {
			ret[i] = 0
		} else {
			if cells[i-1] == cells[i+1] {
				ret[i] = 1
			} else {
				ret[i] = 0
			}
		}
	}

	return ret
}

func main() {
	fmt.Println(prisonAfterNDays([]int{0, 1, 0, 1, 1, 0, 0, 1}, 7))
}
