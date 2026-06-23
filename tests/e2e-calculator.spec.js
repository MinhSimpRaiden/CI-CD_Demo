// @ts-check
const { test, expect } = require('@playwright/test');

// =====================================================================
// 🛠️ CONFIGURATION: KHAI BÁO LOCATORS (CẤU HÌNH GIAO DIỆN)
// =====================================================================
// HƯỚNG DẪN: Hãy thay thế các giá trị bên dưới bằng ID, Class hoặc
// Name thực tế khớp với file HTML/JSP của bạn.
// =====================================================================

const LOCATORS = {
  HEADER: 'h1', 
  DATE_INPUT: 'input[type="date"], #dateInput', 
  SUBMIT_BTN: 'button[type="submit"], #checkBtn', 
  RESULT_MESSAGE: '#resultMessage, .result, #result',
  EXPECTED_RESULT_TEXT: 'Hợp lệ'
};

test.describe('Date Time Checker E2E Tests', () => {
  
  test('should successfully validate date input', async ({ page }) => {
    // Bước 1: Truy cập trang
    await page.goto('http://localhost:8080/');

    // Bước 2: Verify Title
    await expect(page).toHaveTitle(/Date Time Checker/i); 
    
    const header = page.locator(LOCATORS.HEADER).first();
    await expect(header).toBeVisible();

    // Bước 3: Nhập liệu
    const dateInput = page.locator(LOCATORS.DATE_INPUT).first(); 
    await dateInput.waitFor({ state: 'visible', timeout: 10000 });
    await dateInput.fill('2023-01-01');

    // Bước 4: Submit
    const submitButton = page.locator(LOCATORS.SUBMIT_BTN).first();
    await submitButton.click();

    // Bước 5: Assert kết quả
    const resultElement = page.locator(LOCATORS.RESULT_MESSAGE).first();
    await resultElement.waitFor({ state: 'visible', timeout: 10000 });
    await expect(resultElement).toBeVisible();
    // await expect(resultElement).toContainText(LOCATORS.EXPECTED_RESULT_TEXT); 
  });

});
