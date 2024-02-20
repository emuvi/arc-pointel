package pin.libout;

import java.awt.GridBagConstraints;

public class GBCWrap extends GridBagConstraints {

    public GBCWrap(int gridx, int gridy) {
        this.gridx = gridx;
        this.gridy = gridy;
    }

    public GBCWrap setSpan(int gridwidth, int gridheight) {
        this.gridwidth = gridwidth;
        this.gridheight = gridheight;
        return this;
    }

    public GBCWrap setAnchor(int anchor) {
        this.anchor = anchor;
        return this;
    }

    public GBCWrap setFill(int fill) {
        this.fill = fill;
        return this;
    }

    public GBCWrap setWeight(double weightx, double weighty) {
        this.weightx = weightx;
        this.weighty = weighty;
        return this;
    }

    public GBCWrap setInsets(int distance) {
        this.insets = new java.awt.Insets(
                distance, distance, distance, distance);
        return this;
    }

    public GBCWrap setInsets(int top, int left, int bottom, int right) {
        this.insets = new java.awt.Insets(top, left, bottom, right);
        return this;
    }

    public GBCWrap setIpad(int ipadx, int ipady) {
        this.ipadx = ipadx;
        this.ipady = ipady;
        return this;
    }
}
