//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.ruisi.ext.engine.view.emitter.pdf;

import com.itextpdf.text.DocumentException;
import com.ruisi.ext.engine.control.InputOption;
import com.ruisi.ext.engine.cross.CrossBuilder;
import com.ruisi.ext.engine.cross.CrossOutData;
import com.ruisi.ext.engine.cross.CrossWriter2JAVA;
import com.ruisi.ext.engine.init.ExtEnvirContext;
import com.ruisi.ext.engine.view.context.Element;
import com.ruisi.ext.engine.view.context.MVContext;
import com.ruisi.ext.engine.view.context.chart.ChartContext;
import com.ruisi.ext.engine.view.context.cross.CrossReportContext;
import com.ruisi.ext.engine.view.context.form.ButtonContext;
import com.ruisi.ext.engine.view.context.form.CheckBoxContext;
import com.ruisi.ext.engine.view.context.form.DateSelectContext;
import com.ruisi.ext.engine.view.context.form.FileFieldContext;
import com.ruisi.ext.engine.view.context.form.RadioContext;
import com.ruisi.ext.engine.view.context.form.SelectContext;
import com.ruisi.ext.engine.view.context.form.TextFieldContext;
import com.ruisi.ext.engine.view.context.form.TreeContext;
import com.ruisi.ext.engine.view.context.grid.DataGridContext;
import com.ruisi.ext.engine.view.context.gridreport.GridReportContext;
import com.ruisi.ext.engine.view.context.html.BRContext;
import com.ruisi.ext.engine.view.context.html.BoxContext;
import com.ruisi.ext.engine.view.context.html.CustomContext;
import com.ruisi.ext.engine.view.context.html.DivContext;
import com.ruisi.ext.engine.view.context.html.FieldsetContext;
import com.ruisi.ext.engine.view.context.html.ImageContext;
import com.ruisi.ext.engine.view.context.html.IncludeContext;
import com.ruisi.ext.engine.view.context.html.KpiDescContext;
import com.ruisi.ext.engine.view.context.html.LinkContext;
import com.ruisi.ext.engine.view.context.html.TextContext;
import com.ruisi.ext.engine.view.context.html.table.TableContext;
import com.ruisi.ext.engine.view.context.html.table.TdContext;
import com.ruisi.ext.engine.view.context.html.table.TrContext;
import com.ruisi.ext.engine.view.context.tab.TabContext;
import com.ruisi.ext.engine.view.context.tab.TabViewContext;
import com.ruisi.ext.engine.view.emitter.ContextEmitter;
import com.ruisi.ext.engine.view.emitter.chart.ShowJSCharts;
import com.ruisi.ext.engine.view.emitter.excel.ChartData;
import com.ruisi.ext.engine.view.exception.ExtConfigException;
import com.ruisi.ext.engine.wrapper.ExtRequest;
import com.ruisi.ext.engine.wrapper.ExtResponse;
import com.ruisi.ext.engine.wrapper.ExtWriter;
import com.ruisi.ext.engine.wrapper.StringBufferWriterImpl;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;

public class PdfEmitter implements ContextEmitter {
    private ExtWriter a;
    private ExtRequest b;
    private ExtResponse c;
    private PdfLayoutEnginer d;
    private ExtEnvirContext e;
    private InputOption f;
    private ServletContext g;

    public PdfEmitter() {
    }

    @Override
    public void end(Element var1) {
        this.d.buildEnd(var1);
    }

    @Override
    public void endBox(BoxContext var1) {
    }

    @Override
    public void endDiv(DivContext var1) {
    }

    @Override
    public void endFieldset(FieldsetContext var1) {
    }

    @Override
    public void endMV(MVContext var1) {
    }

    @Override
    public void endTab(TabContext var1) {
    }

    @Override
    public void endTable(TableContext var1) {
    }

    @Override
    public void endTabView(TabViewContext var1) {
    }

    @Override
    public void endTd(TdContext var1) {
    }

    @Override
    public void endTr(TrContext var1) {
    }

    @Override
    public ExtWriter getWriter() {
        return null;
    }

    @Override
    public void initialize(ExtWriter var1, ExtRequest var2, ExtResponse var3, ExtEnvirContext var4, InputOption var5, ServletContext var6) throws IOException, DocumentException {
        this.a = var1;
        this.b = var2;
        this.c = var3;
        this.e = var4;
        this.f = var5;
        this.d = new PdfLayoutEnginer(var1, var2, var3, var4, var5);
        this.d.init();
        this.g = var6;
        var4.put("outType", "pdf");
    }

    @Override
    public void rebuildSelect(SelectContext var1) {
    }

    @Override
    public void start(Element var1) throws DocumentException, IOException {
        this.d.buildStart(var1);
    }

    @Override
    public void startBox(BoxContext var1) {
    }

    @Override
    public void startBR(BRContext var1) {
    }

    @Override
    public void startButton(ButtonContext var1) {
    }

    @Override
    public void startChart(ChartContext var1) throws IOException, DocumentException {
        List var2 = var1.loadOptions();
        if (var2.size() != 0) {
            StringBufferWriterImpl var3 = new StringBufferWriterImpl();
            ShowJSCharts var4 = new ShowJSCharts(var3, var1, this.b, this.e, true);
            try {
                var4.show();
            } catch (ExtConfigException e1) {
                e1.printStackTrace();
            }
            ChartData var5 = new ChartData();
            var5.setHeight(Integer.parseInt(var1.getHeight()));
            String[] var6 = ((String)this.b.getAttribute("picinfo")).split("@");
            String[] var10 = var6;
            int var9 = var6.length;

            for(int var8 = 0; var8 < var9; ++var8) {
                String var7 = var10[var8];
                String[] var11 = var7.split(",");
                if (var11[0].equals(var1.getLabel())) {
                    var5.setPicinfo(var11[1]);
                    if (var1.getWidth() != null && var1.getWidth().endsWith("%")) {
                        var5.setWidth(Integer.parseInt(var11[2]));
                    } else {
                        var5.setWidth(Integer.parseInt(var1.getWidth()));
                    }
                    break;
                }
            }

            var5.setContext(this.g);
            if (var1.getTitle() != null) {
                var5.setTitle(var1.getTitle().getText());
            }

            this.d.buildChart(var5);
        }
    }

    @Override
    public void startCheckBox(CheckBoxContext var1) {
    }

    @Override
    public void startCrossReport(CrossReportContext var1) throws DocumentException {
        ExtEnvirContext var2 = this.e;
        CrossBuilder var3 = new CrossBuilder(var1, this.b, var2);
        var3.init();
        CrossWriter2JAVA var4 = new CrossWriter2JAVA(var3, this.a);
        var4.writeCols();
        var4.writeRows();
        CrossOutData var5 = var4.getOutData();
        this.d.buildCrossReport(var5);
    }

    @Override
    public void startDataGrid(DataGridContext var1) {
    }

    @Override
    public void startDateSelect(DateSelectContext var1) throws DocumentException {
        this.d.buildDataSelect(var1);
    }

    @Override
    public void startDiv(DivContext var1) {
    }

    @Override
    public void startFieldset(FieldsetContext var1) {
    }

    @Override
    public void startFileField(FileFieldContext var1) {
    }

    @Override
    public void startGridReport(GridReportContext var1) throws IOException, DocumentException {
        this.d.buildGridReport(var1);
    }

    @Override
    public void startInclude(IncludeContext var1) {
    }

    @Override
    public void startKpiDesc(KpiDescContext var1) {
    }

    @Override
    public void startLink(LinkContext var1) {
    }

    @Override
    public void startMV(MVContext var1) {
    }

    @Override
    public void startRadio(RadioContext var1) {
    }

    @Override
    public void startSelect(SelectContext var1) throws DocumentException {
        this.d.buildSelect(var1);
    }

    @Override
    public void startTab(TabContext var1) {
    }

    @Override
    public void startTable(TableContext var1) {
    }

    @Override
    public void startTabView(TabViewContext var1) {
    }

    @Override
    public void startTd(TdContext var1) {
    }

    @Override
    public void startText(TextContext var1) throws DocumentException {
        this.d.buildText(var1);
    }

    @Override
    public void startTextField(TextFieldContext var1) throws DocumentException {
        this.d.buildTextfield(var1);
    }

    @Override
    public void startTr(TrContext var1) {
    }

    @Override
    public void startTree(TreeContext var1) {
    }

    @Override
    public void startCustom(CustomContext var1) {
        this.d.buildCustom(var1);
    }

    @Override
    public void startImage(ImageContext var1) throws IOException, DocumentException {
        this.d.buildImage(var1);
    }
}
