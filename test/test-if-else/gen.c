#include <stdio.h>
int main() {
  double x;
  x = 5.0;
  if (x > 2.0) {
    printf("%g\n", (double)(10.0));
  } else {
    printf("%g\n", (double)(20.0));
  }
  if (4.0 < 2.0) {
    printf("%g\n", (double)(99.0));
  } else {
    printf("%g\n", (double)(100.0));
  }
  return 0;
}
