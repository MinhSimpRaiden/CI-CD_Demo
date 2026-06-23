// @ts-check
const { test, expect } = require('@playwright/test');

test.describe('Date Time Checker E2E Tests', () => {
  
  test('should successfully validate date input', async ({ page }) => {
    // 1. Truy cập vào http://localhost:8080
    await page.goto('http://localhost:8080/');

    // 2. Xác nhận trang đã tải thành công với Title chính xác
    await expect(page).toHaveTitle(/Date Time Checker/i); 
    
    // LOCATOR: Sửa 'h1' thành thẻ heading thực tế trên giao diện của bạn nếu cần
    const header = page.locator('h1');
    await expect(header).toBeVisible();

    // 3. Điền dữ liệu vào các ô input
    // LOCATOR: Sửa '#dateInput' thành ID hoặc CSS Selector thực tế của ô nhập ngày
    // Ví dụ: page.locator('input[name="date"]')
    const dateInput = page.locator('#dateInput'); 
    await dateInput.fill('2023-01-01');

    // 4. Click vào nút thực thi
    // LOCATOR: Sửa '#checkBtn' thành ID hoặc CSS Selector của nút Kiểm tra
    // Ví dụ: page.getByRole('button', { name: 'Check' })
    const submitButton = page.locator('#checkBtn');
    await submitButton.click();

    // 5. Kiểm tra (Assert) kết quả trả về
    // LOCATOR: Sửa '#resultMessage' thành ID hoặc CSS Selector của phần tử chứa kết quả trả về
    const resultElement = page.locator('#resultMessage');
    
    // Đảm bảo phần tử kết quả hiển thị trên màn hình
    await expect(resultElement).toBeVisible();
    
    // (Tuỳ chọn) Kiểm tra nội dung text của kết quả
    // LOCATOR: Đổi 'Hợp lệ' thành kết quả mong đợi thực tế trên ứng dụng của bạn
    await expect(resultElement).toContainText('Hợp lệ'); 
  });

});
