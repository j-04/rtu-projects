#include <iostream>

// Amdahl's law
void func1(int n, double f) {
    double result = n / (1 + (n - 1) * f);
    std::cout << "Amdahl's law - " << "n : " << n <<  "; f : " << f << "; result : " << result << std::endl;
}

// Gustafson – Barsis's law
void func2(int n, double f) {
    double result = n - (n - 1) * f;
    std::cout << "Gustafson – Barsis's law - " << "n : " << n <<  "; f : " << f << "; result : " << result << std::endl;
}

// Sana-Naya's law
void func3(int n, double f) {
    double result = (f + (1 - f) * 0.5 * n) / (f + (1 - f) * 0.5);
    std::cout << "Sana-Naya's law - " << "n : " << n <<  "; f : " << f << "; result : " << result << std::endl;
}

int main() {
    double f = 0.0;
    int n = 0;
    std::cout << "Enter an f argument: ";
    std::cin >> f;
    std::cout << "Enter an n argument: ";
    std::cin >> n;
    func1(n, f);
    func2(n, f);
    func3(n, f);
    return 0;
}