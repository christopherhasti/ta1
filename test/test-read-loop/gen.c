#include <stdio.h>
int main() {
  double x, y;
  scanf("%lf", &x);
  y = 0.0;
  while (x > 0.0) {
    printf("%g\n", (double)(y));
    y = y + 1.0;
    x = x - 1.0;
  }
  return 0;
}
