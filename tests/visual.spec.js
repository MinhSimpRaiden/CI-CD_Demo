const { test, expect } = require("@playwright/test");

test("homepage visual regression test", async ({ page }) => {
  await page.setViewportSize({ width: 1280, height: 720 });

  await page.goto("/", {
    waitUntil: "networkidle",
  });

  await expect(page).toHaveScreenshot("homepage.png", {
    animations: "disabled",
    fullPage: true,
    maxDiffPixels: 5000,
  });
});
