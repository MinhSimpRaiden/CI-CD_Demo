// @ts-check
const { test, expect } = require('@playwright/test');

test.describe('Calculator / Date Validator E2E Tests', () => {
  
  test('should successfully calculate or validate date input', async ({ page }) => {
    // 1. Truy cập vào http://localhost:8080
    await page.goto('http://localhost:8080/');

    // 2. Xác nhận trang đã tải thành công
    // LOCATOR: Sửa 'Calculator' thành title thực tế trên trang của bạn (ví dụ: 'Date Validator' hoặc 'Spring Boot App')
    await expect(page).toHaveTitle(/Calculator/i); 
    
    // LOCATOR: Sửa 'h1' và text thành thẻ heading hiển thị trên giao diện của bạn
    const header = page.locator('h1');
    await expect(header).toBeVisible();

    // 3. Điền dữ liệu vào các ô input
    // LOCATOR: Sửa '#startDate' thành ID hoặc CSS Selector thực tế của ô nhập ngày bắt đầu
    const startDateInput = page.locator('#startDate'); 
    await startDateInput.fill('2023-01-01');

    // LOCATOR: Sửa '#endDate' thành ID hoặc CSS Selector thực tế của ô nhập ngày kết thúc (nếu có)
    const endDateInput = page.locator('#endDate');
    await endDateInput.fill('2023-12-31');

    // 4. Click vào nút thực thi
    // LOCATOR: Sửa '#calculateBtn' thành ID hoặc CSS Selector của nút Submit/Calculate
    // Hoặc có thể dùng text: page.getByRole('button', { name: 'Calculate' })
    const submitButton = page.locator('#calculateBtn');
    await submitButton.click();

    // 5. Kiểm tra (Assert) kết quả trả về
    // LOCATOR: Sửa '#result' thành ID hoặc CSS Selector của phần tử chứa kết quả trả về
    const resultElement = page.locator('#result');
    
    // Đảm bảo phần tử kết quả hiển thị trên màn hình
    await expect(resultElement).toBeVisible();
    
    // (Tuỳ chọn) Kiểm tra nội dung text của kết quả xem có đúng như mong đợi không
    // LOCATOR: Thay đổi text thành kết quả mong đợi thực tế
    await expect(resultElement).toContainText('Expected Result Text Here');
  });

});
