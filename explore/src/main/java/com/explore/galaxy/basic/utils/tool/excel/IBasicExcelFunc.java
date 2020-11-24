package com.explore.galaxy.basic.utils.tool.excel;

import java.io.File;
import java.io.IOException;

public interface IBasicExcelFunc {
    File exportExcel() throws IOException;

    void importExcel();
}
