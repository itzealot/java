package com.apusic.sicp.util.service;

public interface NumberGenerator {

    /**
     * generate the next system number. the implematation should ensure the unique of number generation, that means, the
     * method should be async-safe.
     *
     * @param prefix
     * @param lastfix
     * @return
     */
    public String generateNextNumber(String prefix, String lastfix);

    /**
     * find the latest system number.
     *
     * @param prefix
     * @param lastfix
     * @return
     */
    public String getLatestNumber(String prefix, String lastfix);

}
